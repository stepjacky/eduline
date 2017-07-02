package org.jackysoft.edu.controller;

import java.util.List;

import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.entity.CourseInGrade;
import org.jackysoft.edu.entity.Grade;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.CourseInGradeService;
import org.jackysoft.edu.service.CourseService;
import org.jackysoft.edu.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/courseingrade")
public class CourseInGradeController extends AbstractController<String, CourseInGrade>{

	
	
	
	@Autowired
	CourseInGradeService service;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	GradeService gradeService;
	
	
	
	
	
	@Override@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = super.index();
		List<Grade> grades    = gradeService.findAll();
		List<Course> courses  = courseService.findAll();
		mav.addObject("grades", grades);
		mav.addObject("courses", courses);
		return mav; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/courses/{inyear}/{grade}")
	public List<Integer> courses(
			@PathVariable("inyear")String inyear,
			@PathVariable("grade")String grade
			) {
		List<CourseInGrade> cigs = service.findCourse(inyear, grade);
		List<Integer> dmap = Lists.newArrayList();
		cigs.stream().forEach(c -> dmap.add(c.getCourse()));	
		return dmap;
		
		
	}

	
	
	
	
	
	
	@Override
	public AbstractService<String, CourseInGrade> getService() {
		return service;
	}

}
