package org.jackysoft.edu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.entity.UserEvent;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.UserEventMapper;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.query.QueryParser;
import org.jackysoft.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

@Service
@Transactional("transactionManager")
public class UserEventService extends AbstractSQLService<String, UserEvent> {

	@Autowired
	private UserEventMapper mapper;

	@Autowired
	private SysUserService userService;

	/***
	 * @param from
	 *            依照视图决定，周视图就是每周的开始，月视图就是每月开始，依次类推
	 * @param to
	 *            依照视图决定，周视图就是每周的结束，月视图就是每月结束，依次类推
	 * @param user
	 *            用户名
	 * @param userType
	 *            用户类型
	 * @return 符合条件的用户事件
	 * 
	 */
	public List<UserEvent> findMyEvents(long from, long to, String user, int userType) {
		// 0 管，1学,2老,3家
		// 0全体,1仅老师，2仅自己,3仅自己并且在一个时间段内重复
		// 0={0,1},1={0,2},2={0,1},3={2-}

		String q = "";
		switch (userType) {
		// 管理员仅仅看全体,仅老师的和自己的
		case 0:
			q = String.format(" eventType=0 OR eventType=1 OR (eventType=2 AND username='%s') OR  (eventType=3 AND username='%s') ",user, user);
			break;
		// 学生仅仅看全体和自己的
		case 1:
			q = String.format(" eventType=0 OR (eventType=2 AND username='%s') OR  (eventType=3 AND username='%s') ",user, user);
			break;
		// 老师仅仅看全体,自己和老师的
		case 2:
			q = String.format(" eventType=0 OR eventType=1 OR (eventType=2 AND username='%s') OR  (eventType=3 AND username='%s') ",user, user);
			break;
		// 家长仅仅看自己孩子和全体的
		case 3: {
			SysUser parent = userService.findById(user);
			q = String.format(" eventType=0 OR username='%s' ", parent.getChildren());
			break;

		}
		default:
			q = " true ";
		}

		List<UserEvent> beans = mapper.findMyEvents(from, to, q);
		return beans;
	}

	@Override
	public PreResult save(UserEvent t) {
		if (t.getRepeated() != 1) {
			return super.save(t);
			
		}

		int week = t.getDayOfweek();
		long start = t.getRepeatStart();
		long end = t.getRepeatEnd();
		for (long s = start; s <= end; s += DateUtils.MILLISSECONDS_OF_ONE_DAY) {
			LocalDate current = DateUtils.fromMillis(s).toLocalDate();
			if (current.getDayOfWeek().getValue() != week)
				continue;
			LocalTime startTime = DateUtils.fromMillis(t.getStarttime()).toLocalTime();
			LocalTime endTime = DateUtils.fromMillis(t.getEndtime()).toLocalTime();
			UserEvent ctl = t.clone();
			ctl.setStarttime(DateUtils.withMillis(LocalDateTime.of(current, startTime)));
			ctl.setEndtime(DateUtils.withMillis(LocalDateTime.of(current, endTime)));
			super.save(ctl);
		}
		return null;

	}
	
	public Pager<UserEvent> myRepeatedEvents(String user,int page){
		if(Strings.isNullOrEmpty(user)) return Pager.EMPTY_PAGER();
		QueryBuilder qb = new QueryBuilder(new QueryParser("username`"+user+";repeated`1"));
	
		qb.setGroupBy("repeatstart,repeatend,dayofweek");
		Pager<UserEvent> pager  = this.findPager(qb, page, 10);
		return pager;
	}

	public void removeRepeated(String exampleId,String user){
		UserEvent example = mapper.findById(exampleId);
		Map<String,String> condMap = Maps.newHashMap();
		condMap.put("username", user);
		condMap.put("repeatStart",example.getRepeatStart()+"");
		condMap.put("repeatEnd",example.getRepeatEnd()+"");
		condMap.put("repeated",example.getRepeated()+"");
		condMap.put("dayOfWeek", example.getDayOfweek()+"");
		QueryBuilder qc = new QueryBuilder(new QueryParser(condMap));
		mapper.deleteByQuery(qc);
	}
	
	
	@Override
	public AbstractMapper<String, UserEvent> getMapper() {
		return mapper;
	}

}
