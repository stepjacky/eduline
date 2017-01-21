package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.UrlPattern;
import org.jackysoft.edu.service.AbstractService;
import org.jackysoft.edu.service.UrlPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/urlpattern")
public class UrlPatternController extends AbstractController<String,UrlPattern>{

	
	@Autowired
	protected UrlPatternService service;
	
	@Override
	public AbstractService<String,UrlPattern> getService() {
		return service;
	}

}