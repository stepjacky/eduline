package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.GroupMember;
import org.jackysoft.edu.entity.HomeWork;
import org.jackysoft.edu.entity.HomeWorkTaken;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.service.GroupMemberService;
import org.jackysoft.edu.service.HomeWorkService;
import org.jackysoft.edu.service.ChapterService;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.query.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.List;


@Controller
@RequestMapping("/homework")
public class HomeWorkController extends AbstractController<String, HomeWork> {


    @Autowired
    protected HomeWorkService service;

    @Autowired
    protected GroupMemberService groupService;

    @Autowired
    protected ChapterService chapterService;

    @Value("${noteDir}")
    protected String noteDir;
    private static final int IMAGE_WIDTH = 800;

    //老师作业按照时间线倒序
    @GetMapping("/teacher/timeline")
    public String teacherHomeworksTimeline(
            @AuthenticationPrincipal SysUser user,
            Model model
    ) {

        List<HomeWork> list = service.teacherHomeworkTimeline(user.getUsername());
        model.addAttribute("list", list);
        return "teacher-homework-timeline";
    }

    //老师作业
    @GetMapping("/teacher/{status}/{page}")
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
    @GetMapping("/student/{status}/{page}")
    public String studentHomeworks(
            @AuthenticationPrincipal SysUser user,
            @PathVariable("page") int page,
            @PathVariable("status") String status,
            Model model
    ) {

        Pager<HomeWorkTaken> pager = service.studentHomeworks(page, user.getName(), status);
        model.addAttribute("pager", pager);
        return "student-homework-pager";
    }

    @PostMapping("/submit/homework/{id}")
    public String submitHomework(
            @PathVariable("id")String id,
            @RequestParam("choice") String choice,
            @RequestParam("file") Part part){
        service.submitHomework(id,choice,part);
        return "homework";
    }

    @PostMapping("/scorehomework/{id}")
    public String readAndScore(
            @PathVariable("id")String id,
            @RequestParam("score")float score,
            @RequestParam("yelp")String yelp
    ){

                service.scoredHomeWork(id,score,yelp);
                return "read";

    }

    //选择班级
    @PostMapping("/select-groups")
    public void selectgroups(
            @RequestParam("exercises")String exercises,
            @AuthenticationPrincipal SysUser owner,
            Model model

    ){

                List<GroupMember> groups =  groupService.findTeacherGroups(owner.getUsername());
                model.addAttribute("groups",groups);


    }

    //执行具体布置作业过程
    @PostMapping("/put-homework")
    public void puthomework(
            @RequestParam("exercises")String exercises,
            @RequestParam("groups")String groups,
            @RequestParam("startdate")long startdate,
            @RequestParam("deaddate")long deaddate,
            @AuthenticationPrincipal SysUser owner,
            Model model



    ){

    }

    @Override
    public AbstractService<String, HomeWork> getService() {
        return service;
    }


}