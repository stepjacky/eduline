package org.jackysoft.edu.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.jackysoft.edu.entity.HomeWork;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.service.base.PreResult;
import org.jackysoft.query.Pager;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.aggregation.Sort;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
public class HomeWorkService extends AbstractMongoService<HomeWork> {

	static final Logger logger = LoggerFactory.getLogger(HomeWorkService.class);
	// 某学生的未交作业
	public Pager<HomeWork> studentUnsubmits(int page, String username) {
		return userHomeWorks(page, "student", username, null, HomeWork.UNSUBMITED, HomeWork.UNSCORED);
	}

	// 某学生的已交并且打过分的作业
	public Pager<HomeWork> studentScored(int page, String username) {
		return userHomeWorks(page, "student", username, null, HomeWork.SUBMITED, HomeWork.SCORED);
	}

	// 某学生的已交并且未打过分的作业
	public Pager<HomeWork> studentUnScored(int page, String username) {
		return userHomeWorks(page, "student", username, null, HomeWork.SUBMITED, HomeWork.UNSCORED);
	}

	// 某老师的所有未交作业
	public Pager<HomeWork> teacherUnsubmits(int page, String workId, String username) {

		return userHomeWorks(page, "teacher", username, workId, HomeWork.UNSUBMITED, HomeWork.UNSCORED);
	}

	// 某老师的已交作业,已打分
	public Pager<HomeWork> teacherScored(int page, String workId, String username) {

		return userHomeWorks(page, "teacher", username, workId, HomeWork.SUBMITED, HomeWork.SCORED);
	}

	// 某老师的已交作业,未打分
	public Pager<HomeWork> teacherUnscored(int page, String workId, String username) {

		return userHomeWorks(page, "teacher", username, workId, HomeWork.SUBMITED, HomeWork.UNSCORED);
	}

	/**
	 * 某老师历次作业
	 * 
	 */
	public Pager<HomeWork> teacherHomeworks(int page, String username) {

		Query<HomeWork> query = dataStore.createQuery(type).field("teacher").equal(username);

		Iterator<HomeWork> itr = dataStore.createAggregation(type)

				.group(Group.id(Group.grouping("teacher"), Group.grouping("workId")),
						Group.grouping("workId", Group.first("workId")),
						Group.grouping("course", Group.first("course")),
						Group.grouping("courseName", Group.first("courseName")),
						Group.grouping("teacher", Group.first("teacher")),
						Group.grouping("groupId", Group.first("groupId")),
						Group.grouping("firetime", Group.first("firetime")),
						Group.grouping("count", new Accumulator("$sum", 1)))
				.sort(new Sort("firetime", -1))
				.match(query)
				.aggregate(HomeWork.class,
						aggregationOptions
						);
		long count = 0;
		while (itr.hasNext()) {
			HomeWork cr = itr.next();
			if (cr == null)
				continue;
			count ++;
		}

		itr = dataStore.createAggregation(type)

				.group(Group.id(Group.grouping("teacher"), Group.grouping("workId")),
						Group.grouping("workId", Group.first("workId")),
						Group.grouping("course", Group.first("course")),
						Group.grouping("courseName", Group.first("courseName")),
						Group.grouping("teacher", Group.first("teacher")),
						Group.grouping("groupId", Group.first("groupId")),
						Group.grouping("groupName",Group.first("groupName")),
						Group.grouping("firetime", Group.first("firetime")),
						Group.grouping("count", new Accumulator("$sum", 1)))
				.sort(new Sort("firetime", -1)).match(query).limit(Pager.DEFAULT_OFFSET)
				.skip((page < 0 ? 0 : page) * Pager.DEFAULT_OFFSET).aggregate(HomeWork.class,aggregationOptions);
		List<HomeWork> dataList = new ArrayList<>();
		while (itr.hasNext()) {
			HomeWork cr = itr.next();
			if (cr == null)
				continue;
			dataList.add(cr);
		}
		Pager<HomeWork> pager = Pager.build(page, count, dataList);
		return pager;

	}

	private Pager<HomeWork> userHomeWorks(int page, String userKey, String userValue, String workId, int submitStatus,
			int scoreStatus) {
		if (Strings.isNullOrEmpty(userKey))
			return Pager.EMPTY_PAGER();
		Query<HomeWork> query = dataStore.createQuery(type).field(userKey).equal(userValue).field("submitStatus")
				.equal(submitStatus).field("scoreStatus").equal(scoreStatus).order("-firetime");
		if (!Strings.isNullOrEmpty(workId)) {
			query.field("workId").equal(workId);
		}
		long count = dataStore.getCount(query);
		List<HomeWork> dataList = query.limit(Pager.DEFAULT_OFFSET).offset((page < 0 ? 0 : page) * Pager.DEFAULT_OFFSET)
				.asList();
		Pager<HomeWork> pager = Pager.build(page, count, dataList);
		return pager;
	}

	// 提交作业
	public void submitHomeWork(String id, String answer, String answerDoc) {
		Query<HomeWork> query = dataStore.createQuery(type).field(Mapper.ID_KEY).equal(new ObjectId(id));
		dataStore.update(query, dataStore.createUpdateOperations(type).set("submitStatus", HomeWork.SUBMITED)
				.set("finishtime", Instant.now().toEpochMilli()).set("answer", answer).set("answerDoc", answerDoc));

	}
	
	public void updateAnswerDoc(String id, String answerDoc){
		Query<HomeWork> query = dataStore.createQuery(type).field(Mapper.ID_KEY).equal(new ObjectId(id));
		dataStore.update(query, dataStore.createUpdateOperations(type)
				.set("submitStatus", HomeWork.SUBMITED)
				.set("answerDoc", answerDoc));
	}

	// 给作业打分
	public void scoredHomeWork(String id, HomeWork work) {
		Query<HomeWork> query = dataStore.createQuery(type).field(Mapper.ID_KEY).equal(new ObjectId(id));
		dataStore.update(query, dataStore.createUpdateOperations(type)
				.set("finishtime", Instant.now().toEpochMilli())
				.set("score", work.getScore())
				.set("scoreStatus", HomeWork.SCORED)
				.set("note", work.getNote())
				.set("answerResult", work.getAnswerResult())
				);
	}
	
	public List<HomeWork> studentLectures(String student){
		List<HomeWork> lists = dataStore.createQuery(type).field("student").equal(student)
		.order("-firetime")
		.asList();
		logger.info("{}", lists);
		return lists;
	}

	@Override
	public List<PreResult> saveAll(List<HomeWork> list) {
		List<PreResult> rs = new ArrayList<>();
		list.forEach(w->  rs.add(save(w)));
		return rs;
	}

	@Override
	public PreResult save(HomeWork e) {
		Iterator<HomeWork> itr =	this.dataStore.createQuery(type)
		.field("teacher").equal(e.getTeacher())
		.field("student").equal(e.getStudent())
		.field("chapter").equal(e.getChapter()).iterator();
		if(itr.hasNext()) return PreResult.FAILURE;
		return super.save(e);
	}
	
	public void updateHeads(String chpId,String heads){
		Query<HomeWork> query = dataStore.createQuery(type)
				.field("chapter").equal(chpId);
		
		dataStore.update(query, dataStore.createUpdateOperations(type)
				.set("modelAnswer", heads)
				);
		
				
	}
	
	
	

}
