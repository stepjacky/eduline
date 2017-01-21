package org.jackysoft.edu.controller;


import java.util.List;

import org.jackysoft.edu.entity.GroupMember;
import org.jackysoft.edu.formbean.MemberBean;
import org.jackysoft.edu.service.AbstractService;
import org.jackysoft.edu.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/groupmember")
public class GroupMemberController extends AbstractController<String,GroupMember>{

	
	@Autowired
	protected GroupMemberService service;
	
	
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("edit");		
		List<MemberBean> members = service.findByGroupId(id);
		MemberBean item= (members==null || members.isEmpty())?null:members.get(0);
		mav.addObject("beans", members);
		mav.addObject("bean", item);
		return mav;
	}






	@Override
	public AbstractService<String,GroupMember> getService() {
		return service;
	}

}