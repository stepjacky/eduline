package org.jackysoft.edu.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.entity.UserEvent;
import org.jackysoft.edu.formbean.UserEventBean;
import org.jackysoft.edu.formbean.UserEventBody;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.UserEventService;
import org.jackysoft.query.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;


@Controller
@RequestMapping("/userevent")
public class UserEventController extends AbstractController<String,UserEvent>{

	
	@Autowired
	protected UserEventService service;
	
	@RequestMapping("/contentof/{id}")
	public ModelAndView contentof(@PathVariable("id")String id){
		ModelAndView mav = new ModelAndView("content");
		if(Strings.isNullOrEmpty(id)) return mav;
		UserEvent ue = service.findById(id);
	    mav.addObject("bean", ue);
		return mav;
	}
	

	@ResponseBody
	@RequestMapping("/myevents")
	public UserEventBean myevents(
			@AuthenticationPrincipal SysUser loginuser,
			@RequestParam("from")long from,
			@RequestParam("to")long to
			){
		
		UserEventBean ueb = new UserEventBean();
		
        List<UserEvent> beans = service.findMyEvents(from, to, loginuser.getUsername(),loginuser.getUserType());
		List<UserEventBody> bodys = beans.stream().map(m->{
			  return new UserEventBody(m);
		}).collect(Collectors.toList());
        ueb.setResult(bodys);
		
		return ueb;
	}


	
    @RequestMapping(value = "/repeated/remove/{id}")
    public ModelAndView removeRepeat(@PathVariable("id")String id,@AuthenticationPrincipal SysUser user){
    	ModelAndView mav = new ModelAndView("remove");
    	service.removeRepeated(id, user.getUsername());
    	return mav;
    }
	
	
	
    @RequestMapping(value = "/repeated/{page}")
	public ModelAndView repeated(
			@PathVariable("page") int page,
			@AuthenticationPrincipal SysUser user) {
		ModelAndView mav = new ModelAndView("pager");
		Pager<UserEvent> pager = service.myRepeatedEvents(user.getUsername(), page);
		mav.addObject("pager", pager);
		return mav;
	}


	@Override
	public AbstractService<String,UserEvent> getService() {
		return service;
	}

}