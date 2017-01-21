package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.Grade;
import org.jackysoft.edu.service.AbstractService;
import org.jackysoft.edu.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/grade")
public class GradeController extends AbstractController<Integer,Grade>{

	
	@Autowired
	protected GradeService service;
	
	@Override
	public AbstractService<Integer,Grade> getService() {
		return service;
	}

}