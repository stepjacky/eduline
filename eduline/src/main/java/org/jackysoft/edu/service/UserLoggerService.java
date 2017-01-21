package org.jackysoft.edu.service;

import org.jackysoft.edu.entity.UserLogger;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.UserLoggerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class UserLoggerService extends AbstractSQLService<String,UserLogger> {

	@Autowired
	private UserLoggerMapper mapper;
	
	@Override
	public AbstractMapper<String, UserLogger> getMapper() {
		return mapper;
	}

}
