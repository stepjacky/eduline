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

import javax.servlet.http.Part;

@Service
public class HomeWorkService extends AbstractMongoService<HomeWork> {

	static final Logger logger = LoggerFactory.getLogger(HomeWorkService.class);

	@Value("${uploaded.location}")
	protected String baseDir;
	@Autowired
	private GroupMemberService groupService;
	//学生的作业
	public Pager<HomeWorkTaken> studentHomeworks(int page, String username,String status) {
		return homeworkByStatus(page, "student", username,"status", EdulineConstant.HoweworkStatus.valueOf(status));
	}


	//老师的作业
	public Pager<HomeWorkTaken> teacherHomeworks(int page, String username,String status) {
		return homeworkByStatus(page, "teacher", username, "status", EdulineConstant.HoweworkStatus.valueOf(status));
	}

	//老师作业时间线
	public List<HomeWork> teacherHomeworkTimeline(String username) {
		Query<HomeWork> query = query(HomeWork.class)
				.field("teacher.value").equal(EdulineConstant.HoweworkStatus.notreaded)
				.field("status").equal(EdulineConstant.HoweworkStatus.notreaded.getKey())
				.order("-publishDate");
		List<HomeWork> list =query.asList();
		return 	list;

	}

	private Pager<HomeWorkTaken> homeworkByStatus(
			int page,
			String participantKey,
			String participantValue,
			String statusKey,
			EdulineConstant.HoweworkStatus status) {

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
	public void submitHomework(String id, String choice, Part part) {

		String fileId = UUID.randomUUID()+".png";
		try(InputStream ins = part.getInputStream()) {
			Files.copy(ins, new File(baseDir, fileId).toPath());
		}catch (IOException e){
			Writer w = new StringWriter();
			PrintWriter pw = new PrintWriter(w);
			e.printStackTrace(pw);
			logger.error(w.toString());

		}
		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(Mapper.ID_KEY)
				.equal(new ObjectId(id));
		dataStore.update(query,
				updates(HomeWorkTaken.class)
						.set("status", EdulineConstant.HoweworkStatus.submited.getKey())
						.set("submitDate", Instant.now().toEpochMilli())
						.set("choice", choice)
						.set("explain", fileId));

	}

	//作业打分
	public void scoredHomeWork(String id, float score,String yelp) {
		Query<HomeWorkTaken> query = query(HomeWorkTaken.class)
				.field(Mapper.ID_KEY).equal(new ObjectId(id));

		HomeWorkTaken slaver = query.get();
		HomeWork master = slaver.getHomework();
		char[] source  = master.getChoice().toCharArray();
		char[] target  = slaver.getChoice().toCharArray();
		int choiceScore = 0;
		for(int i=0;i<source.length;i++){
			if(target.length<(i+1)) break;
			if(source[i]==target[i])choiceScore++;
		}
		dataStore.update(query, updates(HomeWorkTaken.class)
				.set("readDate", Instant.now().toEpochMilli())
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
