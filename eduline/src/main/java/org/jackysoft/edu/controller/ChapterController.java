package org.jackysoft.edu.controller;

import java.util.List;

import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.entity.Textbook;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.service.TextbookService;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chapter")
public class ChapterController extends AbstractController<String, Chapter> {

	@Autowired
	protected ChapterService service;
	@Autowired
	protected TextbookService textbookService;

	private static final int IMAGE_WIDTH=800;


	@Override
	@RequestMapping(value = "/input")
	public ModelAndView input(@RequestParam(value="param",required = false) String param) {
		ModelAndView mav =  super.input(param);
		Textbook book = textbookService.findById(param);
		List<Chapter> roots = service.findByParent("root",param);
		if(roots!=null && !roots.isEmpty()){
			for(Chapter c : roots){
				c.getChildren().addAll(service.findByParent(c.getId(),param));
			}
		}
		mav.addObject("book",book);
		mav.addObject("roots",roots);
		return mav;
	}


	@Override
	public AbstractService<String, Chapter> getService() {
		return service;
	}

}