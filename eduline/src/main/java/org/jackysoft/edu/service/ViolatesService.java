package org.jackysoft.edu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.jackysoft.edu.entity.Grade;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.entity.Violates;
import org.jackysoft.edu.formbean.ChartDataSource;
import org.jackysoft.edu.formbean.ViolateResult;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.ViolatesMapper;
import org.jackysoft.edu.service.base.AbstractSQLService;
import org.jackysoft.edu.service.base.PreResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.utils.DateUtils;
import org.jackysoft.utils.DateUtils.WeekRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;


@Service
@Transactional("transactionManager")
public class ViolatesService extends AbstractSQLService<String, Violates> {

	
	@Autowired
	private ViolatesMapper mapper;
	@Autowired
	private GradeService gservice;
	@Autowired
	private SysUserService userService;

	public ViolateResult accumulatePoints(String student,int grade) {
		
		
		WeekRange wr = DateUtils.weekRange(null);		
		ViolateResult bean = new ViolateResult();
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries(String.format("affirmative`GT`0;student`%s;grade`%d"
				, student,grade));
		Float uppot = mapper.accumulatePoints(qc);
		qc = new QueryBuilder();
		qc.setQueries(String.format("affirmative`LTEQ`0;student`%s;grade`%d"
				, student,grade));
		Float dwpot = mapper.accumulatePoints(qc);
		bean.setUpPoint(uppot==null?0f:uppot);
		bean.setDownPoint(dwpot==null?0f:dwpot);
		bean.setStudent(student);
		bean.setGrade(grade);
		bean.setGradeName(gservice.findById(grade).getName());
		bean.setStudentName(userService.findById(student).getNickname());
		bean.setWeekOfyear(wr.getWeekOfyear());	
		bean.setWeekStart(DateUtils.formatLocalDate(wr.getStart(), null));
		bean.setWeekEnd(DateUtils.formatLocalDate(wr.getEnd(), null));
		return bean;
				
				
	}
	
	
	@Override
	public PreResult save(Violates t) {
		if(t==null)return null;
		Authentication author = SecurityContextHolder.getContext().getAuthentication();
		SysUser teacher = (SysUser) author;
		if(t.getFireTime()==0)t.setFireTime(DateUtils.currentMillis());
		SysUser student = userService.findById(t.getStudent());
		if(student==null)return null;
        Grade grade =  gservice.findById(student.myGrade());
        if(grade!=null) {
			t.setGrade(grade.getId());			
		    t.setGradeName(grade.getName());
		}
        t.setTeacher(teacher.getUsername());
		t.setTeacherName(teacher.getNickname());
		t.setYear(LocalDateTime.now().getYear());
		
		t.setRecordtime(DateUtils.currentMillis());
	    WeekRange wr = DateUtils.weekRange(null);
	    t.setWeekstart(wr.getStart());
	    t.setWeekend(wr.getEnd());
	    t.setDayOfweek(LocalDate.now().getDayOfWeek().getValue());
	    t.setWeekOfyear(wr.getWeekOfyear());
		
		return super.save(t);
	}	

	public Pager<Violates> queryForCurrentGrade(int grade,int eduyear,int page,int offset){
		
		int eyear = (grade-7)+eduyear;
		
		long count = mapper.queryCurrentGradeCount(grade, eyear);
		List<Violates> dataList = mapper.queryCurrentGrade(grade, eyear,page,offset);
	    
		return Pager.build(page, offset, count, dataList);
	
	}
	
	
	 public Set<ChartDataSource> findChartDataSource(int year,int month){
		 List<ChartDataSource> dss = mapper.findChartDataSource(year, month);
		 Set<ChartDataSource> ds = Sets.newTreeSet(new Comparator<ChartDataSource>() {

			@Override
			public int compare(ChartDataSource o1, ChartDataSource o2) {
				
				return new Integer(o1.getGrade()).compareTo(new Integer(o2.getGrade()));
			}
			 
		 });
		 ds.addAll(dss);
		 return ds;
	 }
	

	@Override
	public AbstractMapper<String, Violates> getMapper() {
		return mapper;
	}

}
