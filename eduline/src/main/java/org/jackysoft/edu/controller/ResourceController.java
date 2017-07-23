package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.entity.Resource;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.entity.Textbook;
import org.jackysoft.edu.service.ChapterService;
import org.jackysoft.edu.service.ResourceService;
import org.jackysoft.edu.service.TextbookService;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.query.Pager;
import org.jackysoft.utils.EdulineConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractController<String, Resource>{


    @Autowired
    ResourceService service;

    @Autowired
    TextbookService textbookService;

    @Autowired
    ChapterService chapterService;


    @RequestMapping(value = "/listresource")
    public void list(
            @RequestParam(value="textbook",required = false) String textbook,
            @RequestParam(value="chapter",required = false) String chapter,
            @RequestParam(value="page",required = false,defaultValue = "0")int page,
            @RequestParam(value="commontype",required = false,defaultValue = "personal") String commontype,
            @RequestParam(value="styletype",required = false,defaultValue = "course")String styletype,
            @RequestParam(value="filetype",required = false,defaultValue = "word")String filetype,
            @AuthenticationPrincipal SysUser user,
            Model model
            ) {

        Textbook book = textbookService.findById(textbook);
        logger.info(book);
        if(book!=null) {
            List<Chapter> chapters = chapterService.findByParent("root", textbook);
            logger.info(chapters);
            if (chapters != null && !chapters.isEmpty()) {
                for (Chapter c : chapters) {
                    c.getChildren().addAll(chapterService.findByParent(c.getId(), textbook));
                }
            }
            model.addAttribute("chapters",chapters);
            model.addAttribute("book",book);
        }
        Pager pager = service.findSpecialPager(page,user.getUsername(),chapter,commontype,styletype,filetype);
        List<Textbook> books =  textbookService.findAll();

        model.addAttribute("pager",pager);
        model.addAttribute("books",books);
        model.addAttribute("commontypes", EdulineConstant.Commontype.values());
        model.addAttribute("styletypes", EdulineConstant.Styletype.values());
        model.addAttribute("filetypes", EdulineConstant.filetype.values());

    }

    @PostMapping("/openupload")
    public void openupload(
            Resource resource,
            Model model
            ){
        model.addAttribute("bean",resource);
    }

    @Override
    public AbstractService<String, Resource> getService() {
        return service;
    }
}
