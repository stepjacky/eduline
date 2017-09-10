package org.jackysoft.edu.controller;


import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.jackysoft.edu.entity.*;
import org.jackysoft.edu.service.ExerciseService;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/homework")
public class HomeWorkController extends AbstractController<String, HomeWork> {


    @Autowired
    protected HomeWorkService service;

    @Autowired
    protected GroupMemberService groupService;

    @Autowired
    protected ChapterService chapterService;

    @Autowired
    protected ExerciseService exerciseService;

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


    @PostMapping("/save/homework/{id}")
    public void savehomework( @PathVariable("id")String id,
                              @RequestParam("choice") String choice,
                              @RequestParam("explains[]") List<String> explains,
                              @RequestParam("status")String status
    ){
        service.saveToHomeworkTaken(id,choice,explains, status);

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

    @ResponseBody
    @PostMapping("/scoreexplain")
    public List<Integer> sccoreexplain(
            @RequestParam("taken")String taken,
            @RequestParam("eindex")int eindex,
            @RequestParam("score")int score
    ){
        List<Integer> list = service.updateExplain(taken,eindex,score);
        return list;
    }

    //选择班级
    @RequestMapping("/select-groups")
    public void selectgroups(
            @RequestParam("exercise")String exercise,
            @RequestParam("course") int course,
            @AuthenticationPrincipal SysUser owner,
            Model model

    ){

           List<GroupMember> groups =  groupService.findTeacherGroups(owner.getUsername());
           model.addAttribute("groups",groups);

    }

    //执行具体布置作业过程
    @PostMapping("/put-homework")
    public void puthomework(
            @RequestParam("exercise")String exercise,
            @RequestParam("groups")String groups,
            @RequestParam("course")int course,
            @RequestParam("startdate")long startdate,
            @RequestParam(value ="content",required = false,defaultValue = "")String content,
            @AuthenticationPrincipal SysUser owner,
            Model model



    ){
        if(exercise!=null && groups!=null){
            HomeWork bean = new HomeWork();
            bean.setContent(content);
            bean.setExercise(exercise);
            bean.setGroups(Splitter.on(',')
                    .splitToList(groups)
                    .stream()
                    .filter(s->!Strings.isNullOrEmpty(s))
                    .collect(Collectors.toList()));
            bean.setCourse(course);
            if(startdate>0) {
                bean.setStartdate(startdate);
            }else{
                bean.setStartdate(Instant.now().toEpochMilli());
            }
            bean.setTeacher(new NameValue(owner.getNickname(),owner.getUsername()));
            service.save(bean);
            model.addAttribute("bean",bean);
        }
    }


    //老师作业阅卷习题总览
    @GetMapping("/teacher-summary")
    public void teachersummary(
            @RequestParam("homework")String homework,
            Model model
    ) {

        HomeWork master = service.findById(homework);
        Exercise exercise = exerciseService.findById(master.getExercise());
        List<HomeWorkTaken> takens = service.findSubmitedByHomework(homework);
        List<GroupMember> groups = groupService.findByGroupIds(master.getGroups());

        //每个题答对的人数
        Map<String,Integer> rights = new HashMap<>();


        //每个选择题选项回答的列表
        //key index+answer, value user
        Map<String,List<NameValue>> choiceUser = new HashMap<>();

        //选择题标准答案
        String stdChoice = master.getChoice();

        Map<NameValue,String> userAnswers =
                takens.stream().collect(
                        Collectors.toMap(HomeWorkTaken::getStudent,HomeWorkTaken::getChoice,(e1,e2)->e1));


        for(Map.Entry<NameValue,String> entry : userAnswers.entrySet()){
            NameValue user = entry.getKey();
            String userChoice = entry.getValue();
            if(user==null || Strings.isNullOrEmpty(userChoice)) continue;
            for(int i=0;i<stdChoice.length();i++){
                char src = Character.toUpperCase(stdChoice.charAt(i));
                if(i<userChoice.length()){
                    char dest = Character.toUpperCase(userChoice.charAt(i));
                    //答对人数
                    String rkey = "rgt#"+i;
                    if(dest==src){
                        Integer val = rights.get(rkey);
                        if(val==null){
                            rights.put(rkey,1);
                        }else{
                            rights.put(rkey,val+1);
                        }
                        //某道题答对人数
                    }

                    //统计某个答案都有谁选
                    String key = dest+"#"+i;
                    List<NameValue> ansUsers = choiceUser.get(key);
                    if(ansUsers==null){
                        ansUsers = new ArrayList<>();
                        choiceUser.put(key,ansUsers);
                    }
                    ansUsers.add(new NameValue(user));



                }
                continue;
            }

        }

        Map<String,String> stdChoices = new HashMap<>();
        for(int i=0;i<exercise.getChoice().length();i++){
            stdChoices.put("std#"+i,(exercise.getChoice().charAt(i)+"").toUpperCase());
        }

        model.addAttribute("stdchoices",stdChoices);
        model.addAttribute("rights",rights);
        model.addAttribute("choiceuser",choiceUser);
        model.addAttribute("bean", master);
        model.addAttribute("groups", groups);
        model.addAttribute("exercise", exercise);
        model.addAttribute("takens", takens);
    }

    @Override
    public AbstractService<String, HomeWork> getService() {
        return service;
    }


}