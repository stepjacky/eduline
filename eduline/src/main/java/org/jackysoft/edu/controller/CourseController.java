package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.service.AbstractService;
import org.jackysoft.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/course")
public class CourseController extends AbstractController<Integer,Course>{

	@Autowired
	protected CourseService service;
	
	@RequestMapping(value = "/settype/{id}/{type}")
	public ModelAndView settype(
			@PathVariable("id") String id,
			@PathVariable("type")int type
			) {
		ModelAndView mav = new ModelAndView("update");
		service.changeType(id, type);
		return mav;
	}
	
	
	@Override
	public AbstractService<Integer,Course> getService() {
		return service;
	}

}