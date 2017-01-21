package org.jackysoft.edu.service;

import org.jackysoft.edu.entity.Authority;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.AuthorityMapper;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional("transactionManager")
public class AuthorityService extends AbstractSQLService<String, Authority> {

	
	@Autowired
	private AuthorityMapper mapper;

	
	
	
	@Override@PreAuthorize("hasRole('ROLE_ADMIN')")
	public PreResult save(Authority t) {
		// TODO Auto-generated method stub
		return super.save(t);
	}




	@Override@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void update(Authority t) {
		// TODO Auto-generated method stub
		super.update(t);
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
	public void removeById(String s) {
		// TODO Auto-generated method stub
		super.removeById(s);
	}




	@Override@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updatePartial(String id, String props) {
		// TODO Auto-generated method stub
		super.updatePartial(id, props);
	}




	@Override
	public AbstractMapper<String, Authority> getMapper() {
		return mapper;
	}

}
