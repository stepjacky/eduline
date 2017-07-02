package org.jackysoft.edu.service;

import org.jackysoft.edu.entity.Grade;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.GradeMapper;
import org.jackysoft.edu.service.base.AbstractSQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional("transactionManager")
public class GradeService extends AbstractSQLService<Integer, Grade> {

	
	@Autowired
	private GradeMapper mapper;

		
	@Override
	public AbstractMapper<Integer, Grade> getMapper() {
		return mapper;
	}

}
