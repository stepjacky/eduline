package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.HomeWork;
import org.jackysoft.edu.entity.HomeWorkTaken;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.service.GroupMemberService;
import org.jackysoft.edu.service.HomeWorkService;
import org.jackysoft.edu.service.NoteChapterService;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.query.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/homework")
public class HomeWorkController extends AbstractController<String, HomeWork> {


    @Autowired
    protected HomeWorkService service;

    @Autowired
    protected GroupMemberService groupService;

    @Autowired
    protected NoteChapterService chapterService;

    @Value("${noteDir}")
    protected String noteDir;
    private static final int IMAGE_WIDTH = 800;

    //老师作业
    @RequestMapping("/teacher/homeworks/{status}/{page}")
    public String teacherHomeworks(
            @AuthenticationPrincipal SysUser user,
            @PathVariable("page") int page,
            @PathVariable("status") String status,
            Model model
    ) {

        Pager<HomeWorkTaken> pager = service.teacherHomeworks(page, user.getName(), status);
        model.addAttribute("pager", pager);
        return "teacher-homework-pager";
    }

    @Override
    public AbstractService<String, HomeWork> getService() {
        return service;
    }


}