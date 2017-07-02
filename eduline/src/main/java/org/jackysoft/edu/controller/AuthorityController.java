package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.Authority;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/authority")
public class AuthorityController extends AbstractController<String,Authority>{

	
	@Autowired
	protected AuthorityService service;
	
	@Override
	public AbstractService<String,Authority> getService() {
		return service;
	}

}