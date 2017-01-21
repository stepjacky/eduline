package org.jackysoft.edu.service;

import org.jackysoft.edu.entity.UrlPattern;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.UrlPatternMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional("transactionManager")
public class UrlPatternService extends AbstractSQLService<String, UrlPattern> {

	
	@Autowired
	private UrlPatternMapper mapper;

		
	@Override
	public AbstractMapper<String, UrlPattern> getMapper() {
		return mapper;
	}

}
