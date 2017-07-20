package org.jackysoft.edu.service;

import org.jackysoft.edu.entity.ExamPaper;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.ExamPaperMapper;
import org.jackysoft.edu.service.base.AbstractSQLService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional("transactionManager")
public class ExamPaperService extends AbstractSQLService<String, ExamPaper> {

	
	@Autowired
	private ExamPaperMapper mapper;

	
	
	
		
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ActionResult update(ExamPaper t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}





	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void update(QueryBuilder qc) {
		// TODO Auto-generated method stub
		super.update(qc);
	}





	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void remove(QueryBuilder qc) {
		// TODO Auto-generated method stub
		super.remove(qc);
	}





	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ActionResult removeById(String s) {
		// TODO Auto-generated method stub
	    return 	super.removeById(s);
	}





	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updatePartial(String id, String props) {
		// TODO Auto-generated method stub
		super.updatePartial(id, props);
	}





	@Override
	public AbstractMapper<String, ExamPaper> getMapper() {
		return mapper;
	}

}
