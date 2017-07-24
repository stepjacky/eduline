package org.jackysoft.edu.controller;

import com.google.common.base.Strings;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.entity.Exercise;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.entity.Textbook;
import org.jackysoft.edu.service.ChapterService;
import org.jackysoft.edu.service.ExerciseService;
import org.jackysoft.edu.service.TextbookService;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.utils.EdulineConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.List;

@Controller
@RequestMapping("/exercise")
public class ExerciseController extends AbstractController<String,Exercise> {

    @Autowired
    ExerciseService service;
    @Autowired
    TextbookService textbookService;
    @Autowired
    ChapterService chapterService;

    @ResponseBody
    @PostMapping("/upload-answer")
    public ActionResult uploadanswer(@RequestParam("file") Part part){
        ActionResult result = service.uploadAnswer(part);
        return result;
    }

    @ResponseBody
    @PostMapping("upload-exercise")
    public ActionResult uploadexercise(@RequestParam("file") Part part){
        ActionResult result = service.uploadExercise(part);
        return result;
    }

    @GetMapping("/listexercise")
    public void listexercise(
            @RequestParam(value = "commontype",required = false)String commontype,
            @RequestParam(value = "textbook",required = false)String textbook,
            @RequestParam(value = "chapter",required = false)String chapter,
            @RequestParam(value = "page",defaultValue = "0")int page,
            @AuthenticationPrincipal SysUser owner,
            Model model
    ){
        findExercise(model,textbook,chapter,owner.getUsername(),commontype,page);
    }

    @GetMapping("/homework-exercise")
    public void homeworkexercise(
            @RequestParam(value = "commontype",required = false)String commontype,
            @RequestParam(value = "textbook",required = false)String textbook,
            @RequestParam(value = "chapter",required = false)String chapter,
            @RequestParam(value = "exercises",required = false)String exercises,
            @RequestParam(value = "page",defaultValue = "0")int page,
            @AuthenticationPrincipal SysUser owner,
            Model model
    ){

        findExercise(model,textbook,chapter,owner.getUsername(),commontype,page);

    }


    @PostMapping("/openupload")
    public void openupload(
            Exercise bean,
            Model model
    ){
        model.addAttribute("bean",bean);
    }


    void findExercise(
            Model model,
            String textbook,
            String chapter,
            String username,
            String commontype,
            int page
            ){
        Textbook book = textbookService.getDefaultTextbook(textbook,username);
        if(book==null){
            model.addAttribute("message","请先设置您的课本");
            //return;
        }
        List<Chapter> chapters = chapterService.findByParent("root", textbook);
        if (chapters != null && !chapters.isEmpty()) {
            for (Chapter c : chapters) {
                c.getChildren().addAll(chapterService.findByParent(c.getId(), textbook));
            }
        }
        model.addAttribute("chapters",chapters);
        model.addAttribute("book",book);


        if(!Strings.isNullOrEmpty(chapter)){
            Pager<Exercise> pager =  service.findSpecialPager(page,username,chapter,commontype);
            model.addAttribute("pager",pager);
        }

        model.addAttribute("books",textbookService.findAll());
        model.addAttribute("commontypes",EdulineConstant.Commontype.values());
    }
    @Override
    public AbstractService<String, Exercise> getService() {
        return service;
    }
}
