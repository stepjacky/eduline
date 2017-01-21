package org.jackysoft.edu.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Part;

import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.entity.ExamPaper;
import org.jackysoft.edu.service.AbstractService;
import org.jackysoft.edu.service.CourseService;
import org.jackysoft.edu.service.ExamPaperService;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.query.QueryParser;
import org.jackysoft.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@Controller
@RequestMapping("/exampaper")
public class ExamPaperController extends AbstractController<String,ExamPaper>{

	
	@Autowired
	protected ExamPaperService service;
		
	@RequestMapping(value = "/collect/save", method = RequestMethod.POST)
	public ModelAndView collectform(ExamPaper bean,@RequestParam("file") Part[] part) throws IOException {
		
		ModelAndView mav = new ModelAndView("redirect:/exampaper/pager/0?ajax=false");
		if(bean==null) return mav;
		Arrays.asList(part)
		.stream()
		.filter(p->"file".equals(p.getName()))
		.forEach(p->processUpload(bean,p));
		
		return mav;
	}
	
	
	protected void processUpload(ExamPaper bean,Part part) {
		bean.setName(part.getSubmittedFileName());
		String realPath = UUID.randomUUID().toString()+part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf('.'));
		bean.setRealPath(realPath);
		bean.setFileType(part.getContentType());
		bean.setSize(part.getSize());
		bean.setFireTime(DateUtils.currentMillis());
		try {
			String path = String.format("%s%s%s", filelocation,File.separator,realPath);
			Files.copy(part.getInputStream(), 
					new File(path).toPath());
			logger.info(path);
		} catch (IOException e) {
			logger.error(e);
		}
		getService().save(bean);
	}
	
	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping(value = "/student/{page}")
	public ModelAndView studentindex(
			@PathVariable int page,
			@RequestParam(value="year",defaultValue="")String year,
			@RequestParam(value="course",defaultValue="") String course,
			@RequestParam(value="category",defaultValue="")String category
			
			){
		
		
		ModelAndView mav = new ModelAndView("studentindex");
		List<Course> courses = courseService.findAll();
		List<String> querybf = Lists.newArrayList();
		
		Map<String,String> paramap = Maps.newHashMap();
		paramap.put("year", year);
		paramap.put("course",course);
		paramap.put("category", category);
		paramap.entrySet().stream()
		.filter(e->!Strings.isNullOrEmpty(e.getValue()))
		.forEach(e->querybf.add(e.getKey()+'`'+e.getValue()));
        course=Strings.isNullOrEmpty(course)?"0":course;
		Course tag = courseService.findById(Integer.parseInt(course));
		mav.addObject("categories", Arrays.asList("IGCSE","AS","A2"));
		mav.addObject("courses", courses);
	
		
		Pager<ExamPaper> pager = null;
		if(querybf.isEmpty()) {
			pager = getService().findByPager(page, 10, false);
			mav.addObject("year", LocalDate.now().getYear());
			mav.addObject("course", 0);
			mav.addObject("category", "A2");
		}else {
			QueryBuilder qc = new QueryBuilder();
			qc.setQueries(Joiner.on(';').join(querybf));
			qc.setOrders("fireTime desc,year desc");
			pager = getService().findPager(qc, page, 10);
			mav.addObject("year", year);
			mav.addObject("course", course);
			mav.addObject("category", category);
		}
		
		mav.addObject("courseName", tag==null?"未选择":tag.getName());
		mav.addObject("pager", pager);
		mav.addObject("qstring",String.format("course=%s&year=%s&category=%s", course,year,category));
		return mav;
	}
	
	
	
	
	
	@Override
	@RequestMapping(value = "/query/{page}")
	public ModelAndView query(@RequestParam(value = "query", required = false, defaultValue = "") String query,
			@RequestParam(value = "group", required = false, defaultValue = "") String group,
			@RequestParam(value = "order", required = false, defaultValue = "") String order,
			@PathVariable int page,
			@RequestParam(value = "offset", required = false, defaultValue = "10") int offset,
			@RequestParam(value = "ajax", required = false, defaultValue = "true") boolean ajax) {
		
		ModelAndView mav = super.query(query, group, order, page, offset, ajax);
		QueryParser qeb = new QueryParser(query);
		List<Course> courses = courseService.findAll();
		String course   = qeb.findValue("course");
		String year     = qeb.findValue("year");
		String category = qeb.findValue("category");
		course=Strings.isNullOrEmpty(course)?"0":course;
		Course tag = courseService.findById(Integer.parseInt(course));
		mav.addObject("categories", Arrays.asList("IGCSE","AS","A2"));
		mav.addObject("courses", courses);
		mav.addObject("year", year);
		mav.addObject("category", category);
		mav.addObject("courseName", tag==null?"未选择":tag.getName());		
		return mav;
	}


	@Override
	public AbstractService<String,ExamPaper> getService() {
		return service;
	}

}