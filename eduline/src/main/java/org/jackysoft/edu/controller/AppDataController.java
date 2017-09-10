package org.jackysoft.edu.controller;

import com.google.common.base.Strings;
import org.jackysoft.edu.entity.*;
import org.jackysoft.edu.form.bean.ViolateBean;
import org.jackysoft.edu.formbean.StudentScoreCard;
import org.jackysoft.edu.formbean.ViolateResult;
import org.jackysoft.edu.message.Message;
import org.jackysoft.edu.service.*;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.utils.EdulineConstant;
import org.jackysoft.utils.SchoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class AppDataController {

    static final Map<String, String> tokenSession = new HashMap<>();

    static final Message TOKEN_INVALID = new Message(Message.BAD_CODE,"登录过期,请重新登录",null);

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected SysUserService userService;

    @Autowired
    protected CourseInGradeService courseInService;

    @Autowired
    protected GroupMemberService memberService;

    @Autowired
    protected ResourceService resourceService;

    @Autowired
    protected HomeWorkService homeWorkService;

    @Autowired
    protected UploadService uploadService;

    @Autowired
    protected ExamScoreService examScoreService;

    @Autowired
    protected ViolatesService violatesService;

    @PostMapping("/rest/login")
    public Message login(
            @RequestParam("username")String username,
            @RequestParam("password")String password){
        if(Strings.isNullOrEmpty(username)||Strings.isNullOrEmpty(password)){
            return new Message(Message.BAD_CODE,null,"登录失败,用户名不能空");
        }
        SysUser user = userService.findById(username);
        if(user==null) return new Message(Message.BAD_CODE,null,"用户名不存在");
        if(!passwordEncoder.matches(password,user.getPassword()))
            return new Message(Message.BAD_CODE,null,"密码错误");
        String token = UUID.randomUUID().toString();
        tokenSession.put(token,username);
        return new Message(Message.OK_CODE,token,"登录成功");

    }


    @GetMapping("/rest/courses")
    public Message findcourses(
          @RequestParam("token")String token
    ){
         if(!isTokenValid(token)){
             return TOKEN_INVALID;
         }
         String user = tokenSession.get(token);

         List<CourseInGrade> grades = courseInService.findCourse(SchoolUtils.getInyear(user)+"",SchoolUtils.getGrade(user)+"");

         return new Message(Message.OK_CODE,"获取成功",token,grades);
    }

    @GetMapping("/rest/resources")
    public Message myresources(
            @RequestParam("token")String token,
            @RequestParam("course")int  course,
            @RequestParam("page") int page
    ){

              if(!isTokenValid(token)){
                  return TOKEN_INVALID;
              }
              String user = tokenSession.get(token);
              GroupMember groups =   memberService.findGroupParticipated(user,SchoolUtils.getGrade(user),course);
              List<Resource> resources = resourceService.findforStudent(groups.getTeacher(),SchoolUtils.getGrade(user),course,page);
              return new Message(Message.OK_CODE,token,"获取资源成功",resources);

    }

    @GetMapping("/rest/homeworks")
    public Message homeworks(
            @RequestParam("token")String token,
            @RequestParam("page")int page
            ){
        if(!isTokenValid(token)){
            return TOKEN_INVALID;
        }
        String user = tokenSession.get(token);
        List<HomeWorkTaken> list =  homeWorkService.findforStudent(user,page);
        return new Message(Message.OK_CODE,token,"获取作业成功",list);
    }

    @GetMapping("/rest/homework/taken")
    public Message homeworktaken(
            @RequestParam("token")String token,
            @RequestParam("id")String takenId

    ){
        if(!isTokenValid(token)){
            return TOKEN_INVALID;
        }

        HomeWorkTaken taken = homeWorkService.findforStudent(takenId);

        return new Message(Message.OK_CODE,token,"获取学生作业成功",taken);
    }

    @PostMapping("/rest/save/homework")
    public Message savehomework(
            @RequestParam("token")String token,
            @RequestParam("id")String takenId,
            @RequestParam("explains[]") List<String> explains,
            @RequestParam("choices")String choices,
            @RequestParam("submit")boolean submit


    ){
        if(!isTokenValid(token)){
            return TOKEN_INVALID;
        }
        homeWorkService.saveToHomeworkTaken(takenId,choices,explains,submit?EdulineConstant.HoweworkStatus.submited.getKey():null);
        return new Message(Message.OK_CODE,token,
                submit?"作业已提交":"作业已保存");
    }


    @GetMapping("/rest/examscores")
    public Message examscores(
            @RequestParam("token")String token,
            @RequestParam("page") int page
    ){

        if(!isTokenValid(token)){
            return TOKEN_INVALID;
        }
        String user = tokenSession.get(token);
        StudentScoreCard bean = examScoreService.findStudentScoreCard(user, page);
        return new Message(Message.BAD_CODE,token,"获取成绩成功",bean);
    }

    @GetMapping("/rest/violates")
    public Message violates(
            @RequestParam("token")String token,
            @RequestParam("page") int page
    ){

        if(!isTokenValid(token)){
            return TOKEN_INVALID;
        }
        String user = tokenSession.get(token);
        QueryBuilder qc = new QueryBuilder();
        qc.setQueries("student`"+user);
        qc.setOrders("fireTime desc,affirmative desc");
        List<Violates> beans = violatesService.findAll(qc,page,Pager.DEFAULT_OFFSET);
        ViolateResult vr = violatesService.accumulatePoints(user, SchoolUtils.getGrade(user));
        ViolateBean bean = new ViolateBean();
        bean.setDetails(beans);
        bean.setPoint(vr);
        bean.setCurrent(page);
        return new Message(Message.BAD_CODE,token,"获取行为规范成功",bean);
    }


    @PostMapping(value = "/rest/image/upload")
    public Message uploadImage(@RequestParam("file") Part part){
        ActionResult result = uploadService.upload(part);
        return new Message(Message.OK_CODE,null,"文件上传成功",result);
    }



    boolean isTokenValid(String token) {
        if(Strings.isNullOrEmpty(token)) return false;
        String userid = tokenSession.get(token);
        return !Strings.isNullOrEmpty(userid);
    }

}
