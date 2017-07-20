package org.jackysoft.edu.service;

import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.CourseMapper;
import org.jackysoft.edu.service.base.AbstractSQLService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional("transactionManager")
public class CourseService extends AbstractSQLService<Integer, Course> {

	
	@Autowired
	private CourseMapper mapper;

	
		
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ActionResult update(Course t) {
		// TODO Auto-generated method stub
	return 	super.update(t);
	}



	@Override@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void update(QueryBuilder qc) {
		// TODO Auto-generated method stub
		super.update(qc);
	}



	@Override@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void remove(QueryBuilder qc) {
		// TODO Auto-generated method stub
		super.remove(qc);
	}



	@Override@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ActionResult removeById(Integer s) {
		// TODO Auto-generated method stub
		return super.removeById(s);
	}



	@Override@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updatePartial(Integer id, String props) {
		// TODO Auto-generated method stub
		super.updatePartial(id, props);
	}

	
	public void changeType(String id,int type){
		mapper.setType(id, type);
	}


	@Override
	public AbstractMapper<Integer, Course> getMapper() {
		return mapper;
	}

}
