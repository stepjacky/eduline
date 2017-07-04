package org.jackysoft.edu.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.jackysoft.edu.entity.*;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.utils.HomeworkConstant;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
public class HomeWorkService extends AbstractMongoService<HomeWork> {

	static final Logger logger = LoggerFactory.getLogger(HomeWorkService.class);

	@Autowired
	private GroupMemberService groupService;
	//某学生的作业
	public Pager<HomeWorkTaken> studentHomeworks(int page, String username,String status) {
		return homeworkByStatus(page, "student", username,"status", HomeworkConstant.HoweworkStatus.valueOf(status));
	}


	//某老师的作业
	public Pager<HomeWorkTaken> teacherHomeworks(int page, String username,String status) {
		return homeworkByStatus(page, "teacher", username, "status", HomeworkConstant.HoweworkStatus.valueOf(status));
	}

	private Pager<HomeWorkTaken> homeworkByStatus(
			int page,
			String participantKey,
			String participantValue,
			String statusKey,
			HomeworkConstant.HoweworkStatus status) {

		if (Strings.isNullOrEmpty(participantKey))
			return Pager.EMPTY_PAGER();
		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(participantKey+".value").equal(participantValue)
				.field(statusKey)
				.equal(status.getKey())
				.order("-submitDate");

		long count = dataStore.getCount(query);
		List<HomeWorkTaken> dataList = query.limit(Pager.DEFAULT_OFFSET).offset((page < 0 ? 0 : page) * Pager.DEFAULT_OFFSET)
				.asList();
		Pager<HomeWorkTaken> pager = Pager.build(page, count, dataList);
		return pager;
	}

	//交作业
	public void submitHomework(String id, List<String> choice, List<String> explain) {
		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(Mapper.ID_KEY)
				.equal(new ObjectId(id));
		dataStore.update(query,
				updates(HomeWorkTaken.class)
						.set("status", HomeworkConstant.HoweworkStatus.submited.getKey())
						.set("submitDate", Instant.now().toEpochMilli())
						.set("choice", choice)
						.set("explain", explain));

	}


	//更新解答题作业答案
	public void updateExplain(String id, List<String> values){
		updateAnwser(id,"explain",values);
	}

	//更新选择题答案
	public void updateChoice(String id,List<String> values){
		updateAnwser(id,"choice",values);
	}


	private final void updateAnwser(String id,String keyName,List<String> values){
		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(Mapper.ID_KEY).equal(new ObjectId(id));
		dataStore.update(query,
				updates(HomeWorkTaken.class)
						.set(keyName, values));
	}


	//作业打分
	public void readedHomeWork(String id, HomeWorkTaken work) {
		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(Mapper.ID_KEY).equal(new ObjectId(id));
		dataStore.update(query, updates(HomeWorkTaken.class)
				.set("readDate", Instant.now().toEpochMilli())
				.set("score", work.getScore())
				.set("status", HomeworkConstant.HoweworkStatus.readed.getKey())
				.set("yelp", work.getYelp()));
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
				) return ActionResult.FAILURE;
		ActionResult rst = super.save(homeWork);
		SysUser owner = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(String gid:homeWork.getGroups()){
			List<GroupMember> tmp = groupService.findMembersOfGroup(gid);
			if(tmp==null || tmp.isEmpty()) continue;
			tmp.forEach(gm->{
				HomeWorkTaken taken = new HomeWorkTaken();
				taken.setStudent(new NameValue(gm.getStudentName(),gm.getStudent()));
				taken.setHomework(homeWork);
				taken.setTeacher(new NameValue(owner.getNickname(),owner.getUsername()));
				dataStore.save(taken);
			});
		}
		return rst;
	}

}
