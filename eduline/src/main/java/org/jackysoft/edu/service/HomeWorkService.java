package org.jackysoft.edu.service;

import java.io.*;
import java.nio.file.Files;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.jackysoft.edu.entity.*;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.utils.EdulineConstant;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Part;

@Service
public class HomeWorkService extends AbstractMongoService<HomeWork> {

	static final Logger logger = LoggerFactory.getLogger(HomeWorkService.class);

	@Value("${uploaded.location}")
	protected String baseDir;
	@Autowired
	private GroupMemberService groupService;
	@Autowired
	private CourseService courseService;

	//学生的作业
	public Pager<HomeWorkTaken> studentHomeworks(int page, String username,String status) {
		return homeworkByStatus(page, "student", username, EdulineConstant.HoweworkStatus.valueOf(status));
	}


	//老师的作业
	public Pager<HomeWorkTaken> teacherHomeworks(int page, String username,String status) {
		return homeworkByStatus(page, "teacher", username,  EdulineConstant.HoweworkStatus.valueOf(status));
	}

	//老师作业时间线
	public List<HomeWork> teacherHomeworkTimeline(String username) {
		Query<HomeWork> query = query(HomeWork.class)
				.field("teacher.value").equal(username)
				.field("status").equal(EdulineConstant.HoweworkStatus.notreaded.getKey())
				.order("-startdate");
		List<HomeWork> list =query.asList();
		return 	list;

	}

	private Pager<HomeWorkTaken> homeworkByStatus(
			int page,
			String participantKey,
			String participantValue,
			EdulineConstant.HoweworkStatus status) {

		if (Strings.isNullOrEmpty(participantKey))
			return Pager.EMPTY_PAGER();
		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(participantKey+".value").equal(participantValue)
				.field("status")
				.equal(status.getKey())
				.order("-submitdate");

		long count = dataStore.getCount(query);
		List<HomeWorkTaken> dataList = query.limit(Pager.DEFAULT_OFFSET).offset((page < 0 ? 0 : page) * Pager.DEFAULT_OFFSET)
				.asList();
		Pager<HomeWorkTaken> pager = Pager.build(page, count, dataList);
		return pager;
	}

	//交作业
	public void saveToHomework(String id, String choice, List<String> explains,String status) {

		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(Mapper.ID_KEY)
				.equal(new ObjectId(id));
		HomeWorkTaken taken = query.get();
		if(taken==null) return;

		dataStore.update(query,
				updates(HomeWorkTaken.class)
						.set("status", status)
						.set("submitdate", Instant.now().toEpochMilli())
						.set("choice", choice)
						.set("explains", explains));

	}

	//作业打分
	public void scoredHomeWork(String id, float score,String yelp) {
		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(Mapper.ID_KEY).equal(new ObjectId(id));

		HomeWorkTaken slaver = query.get();
		HomeWork master = findById(slaver.getHomework());
		char[] source  = master.getChoice().toCharArray();
		char[] target  = slaver.getChoice().toCharArray();
		int choiceScore = 0;
		for(int i=0;i<source.length;i++){
			if(target.length<(i+1)) break;
			if(Character.toLowerCase(source[i])==
				Character.toLowerCase(target[i]))
				choiceScore++;
		}
		dataStore.update(query, updates(HomeWorkTaken.class)
				.set("readdate", Instant.now().toEpochMilli())
				.set("score", score+choiceScore)
				.set("status", EdulineConstant.HoweworkStatus.readed.getKey())
				.set("yelp", yelp));
	}

	@Override
	public List<ActionResult> saveAll(List<HomeWork> list) {
		List<ActionResult> rs = new ArrayList<>();
		list.forEach(w->  rs.add(save(w)));
		return rs;
	}

	@Override
	public ActionResult save(HomeWork homeWork) {

		if(homeWork==null
				|| homeWork.getGroups()==null
				|| homeWork.getGroups().isEmpty()
				|| Strings.isNullOrEmpty(homeWork.getExercise())

				) {
			ActionResult result = new ActionResult();
			result.put("message","作业条件不满足,没用习题或者没用班级");
			result.put("flag",false);
			return result;
		}
		Course course = courseService.findById(homeWork.getCourse());

		if(course!=null){
			homeWork.setName(homeWork.getTeacher().getName()+" "+course.getName());
		}else{
			homeWork.setName(homeWork.getTeacher().getName()+" 未知课程");
		}

		SysUser owner = (SysUser) SecurityContextHolder.getContext().getAuthentication();
		List<HomeWorkTaken> takens = new ArrayList<>();
		int amount = 0;
		for(String gid:homeWork.getGroups()){
			List<GroupMember> tmp = groupService.findMembersOfGroup(gid);
			if(tmp==null || tmp.isEmpty()) continue;
			for (GroupMember groupMember : tmp) {
				HomeWorkTaken taken = new HomeWorkTaken();
				taken.setStudent(new NameValue(groupMember.getStudentName(),groupMember.getStudent()));
				taken.setTeacher(new NameValue(owner.getNickname(),owner.getUsername()));
				takens.add(taken);
				amount++;
			}

		}
		homeWork.setAmount(amount);
		homeWork.setStatus(EdulineConstant.HoweworkStatus.notreaded.getKey());
		ActionResult rst = super.save(homeWork);
		for (HomeWorkTaken taken : takens) {
			taken.setHomework(homeWork.getId());
			dataStore.save(taken);
		}
		return rst;
	}

	public List<HomeWorkTaken> findSubmitedByHomework(String homework){
		if(Strings.isNullOrEmpty(homework)) return new ArrayList<>();
		List<HomeWorkTaken> list= query(HomeWorkTaken.class)
				.field("homework").equal(homework)
				.field("status").equal(EdulineConstant.HoweworkStatus.submited.getKey())
		.asList();
		return list;

	}

	@Override
	public void beforeInput(ModelAndView mav) {
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication();
		List<GroupMember> lists = groupService.findTeacherGroups(user.getUsername());
	    mav.addObject("groups",lists);
	}
}
