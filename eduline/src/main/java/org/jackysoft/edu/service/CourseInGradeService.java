package org.jackysoft.edu.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jackysoft.edu.entity.CourseInGrade;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.CourseInGradeMapper;
import org.jackysoft.edu.mapper.CourseMapper;
import org.jackysoft.edu.mapper.GradeMapper;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

@Service
@Transactional("transactionManager")
public class CourseInGradeService extends AbstractSQLService<String, CourseInGrade>{

	@Autowired
	private CourseInGradeMapper mapper;
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private GradeMapper gradeMapper;
	
	
	
	
	@Override
	public PreResult save(CourseInGrade t) {
		if(t==null) return null;
		t.setCourseName(courseMapper.findById(t.getCourse()).getName());
		t.setGradeName(gradeMapper.findById(t.getGrade()).getName());			
		return super.save(t);
	}
	
	
	

    @Override
	public List<PreResult> saveAll(List<CourseInGrade> list) {
    	if(list==null || list.size()==0) return null;
    	Iterator<CourseInGrade> itr = list.iterator();
    	CourseInGrade b = null;
    	for(;itr.hasNext();) {
    		b = itr.next();
    		if(b!=null) {
    			break;
    		}
    	}
    	QueryBuilder qb ;
		Map<String,String> map = Maps.newHashMap();
		map.put("inyear", b.getInyear()+"");
		map.put("grade",b.getGrade()+"");
		qb = new QueryBuilder(map);	
		super.remove(qb);
		return super.saveAll(list);
	}




	public List<CourseInGrade> findCourse(String inyear,String grade){    	
    	QueryBuilder qb ;
		Map<String,String> map = Maps.newHashMap();
		map.put("inyear", inyear);
		map.put("grade",grade);
		qb = new QueryBuilder(map);
		List<CourseInGrade> cigs = findAll(qb);
		return cigs;
    	
    }



	@Override
	public AbstractMapper<String, CourseInGrade> getMapper() {
		return mapper;
	}

}
