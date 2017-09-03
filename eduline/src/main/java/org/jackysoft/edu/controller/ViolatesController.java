package org.jackysoft.edu.controller;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.jackysoft.edu.entity.GroupMember;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.entity.Violates;
import org.jackysoft.edu.extend.jasperreport.JRRawDataSource;
import org.jackysoft.edu.formbean.ChartDataSource;
import org.jackysoft.edu.formbean.ViolateBean;
import org.jackysoft.edu.formbean.ViolateResult;
import org.jackysoft.edu.formbean.ViolatesCard;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.GroupMemberService;
import org.jackysoft.edu.service.SysUserService;
import org.jackysoft.edu.service.ViolatesService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.query.QueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import net.sf.jasperreports.engine.JRDataSource;

@Controller
@RequestMapping("/violates")
public class ViolatesController extends AbstractController<String,Violates>{

	
	@Autowired
	protected ViolatesService service;
	@Autowired
	protected GroupMemberService groupService;
    @Autowired
    protected SysUserService userService;
	

	@RequestMapping("/userinput")
	public ModelAndView userinput(HttpServletRequest request) {
		
		return  new ModelAndView("userinput");
	}
	
	
	@Autowired@Named("userviolate")
	protected JasperReportsPdfView userviolate;
	
	@RequestMapping(value = "/reporter/userviolate.jasper", method = RequestMethod.GET)
	public ModelAndView reporter(@RequestParam("query")String query) {
		Map<String, Object> model = new HashMap<>();
		JRDataSource ds = this.retriveJRDataSource(query);		
		model.put("datasource", ds);		
		ModelAndView mav = new ModelAndView(userviolate,model);
		return mav;
	}
	
		
	@Override
	JRDataSource retriveJRDataSource(String query) {
	    if(Strings.isNullOrEmpty(query)) return new JRRawDataSource<ViolatesCard>(new ViolatesCard());
	    ViolatesCard card = new ViolatesCard();
	    SysUser user = userService.findById(query);
	    card.setTitle(String.format("%s行为规范统计表", user.getNickname()));
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("student`"+query);
		qc.setOrders("fireTime desc,affirmative desc");
		List<Violates> beans = service.findAll(qc);
		List<ViolateBean> vbeans = beans.stream().map(ViolateBean::new).collect(Collectors.toList());
		card.setBeans(vbeans);
	    return new JRRawDataSource<ViolatesCard>(card);
	}




	@RequestMapping(value = "/violate/{page}")
	public ModelAndView violateindex(
			@PathVariable int page) {
		ModelAndView mav = new ModelAndView("index");
		Authentication author = SecurityContextHolder.getContext().getAuthentication();
	    QueryBuilder qc = new QueryBuilder();
       	qc.setQueries("teacher`"+author.getName());
       	qc.setGroupBy("groupId");
		Pager<GroupMember> pager = groupService.findPager(qc, page, 10);		
		mav.addObject("pager", pager);
		return mav;
	}


	
	@RequestMapping(value = "/group/input/{groupId}")
    public ModelAndView violatesinput(
    		@PathVariable String groupId,
    		@AuthenticationPrincipal SysUser loginuser
    		) {
		ModelAndView mav = new ModelAndView("input");
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("teacher`"+loginuser.getName()+";groupId`"+groupId);
		List<GroupMember> members = groupService.findAll(qc);
		mav.addObject("members", members);
		return mav;
    }


	@RequestMapping(value = "/student/{page}")
	public ModelAndView studentindex(
			@PathVariable("page") int page) {
		ModelAndView mav = new ModelAndView("studentindex");
		SysUser author = (SysUser) SecurityContextHolder.getContext().getAuthentication();
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("student`"+author.getName());
		qc.setOrders("fireTime desc,affirmative desc");
		Pager<Violates> pager = getService().findPager(qc, page, 10);		
		ViolateResult vr = service.accumulatePoints(author.getUsername(), author.getGrade());
		mav.addObject("pager", pager);
		mav.addObject("mypoints", vr);
		return mav;
	}
	
	
	@RequestMapping(value = "/parents/{page}")
	public ModelAndView parentsindex(
			@PathVariable("page") int page) {
		ModelAndView mav = new ModelAndView("parentsindex");
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication();
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("student`"+user.getChildren());
		qc.setOrders("fireTime desc,affirmative desc");
		Pager<Violates> pager = getService().findPager(qc, page, 10);	
		SysUser child = userService.findById(user.getChildren());
		ViolateResult points = service.accumulatePoints(child.getUsername(), child.getGrade());
		mav.addObject("pager", pager);
		mav.addObject("child", child);
		mav.addObject("mypoints", points);
		return mav;
	}

	@RequestMapping(value = "/parents/home")
	public ModelAndView parentshome() {
		ModelAndView mav = new ModelAndView("parentshome");		
		return mav;
	}

	
	@Override
	@RequestMapping(value = "/query/{page}")
	public ModelAndView query(
			@RequestParam(value = "query", required = false, defaultValue = "") String query,
			@RequestParam(value = "group", required = false, defaultValue = "") String group,
			@RequestParam(value = "order", required = false, defaultValue = "recordtime desc") String order,
			@PathVariable("page") int page,
			@RequestParam(value = "offset", required = false, defaultValue = "10") int offset,
			@RequestParam(value = "ajax", required = false, defaultValue = "true") boolean ajax) {
		ModelAndView mav = new ModelAndView("query");	
		QueryParser qeb = new QueryParser(query);
	
		String studentName = qeb.findValue("studentName");
		SysUser child;
		ViolateResult points = new ViolateResult();
		if(studentName!=null) {
			child = userService.findByNickname(studentName);
			logger.info(studentName);
			logger.info(child);
			if(child!=null) {
				query+=";grade`"+child.getGrade();
				points = service.accumulatePoints(child.getUsername(), child.getGrade());					
			}
		}
		Pager<Violates> pager = queryPager(query,group,order, page, offset, false);
		mav.addObject("query", query);
		mav.addObject("pager", pager);
		mav.addObject("qstring", String.format("query=%s&group=%s&order=%s", query,group,order));
		mav.addObject("ajax", false);
		
		String username = qeb.findValue("student");
		child = userService.findById(username);		
		if(child==null) {
			username = qeb.findValue("studentName");
			child = userService.findByNickname(username);
			if(child!=null) {
				points = service.accumulatePoints(child.getUsername(), child.getGrade());		
			}			
		}
		
		mav.addObject("mypoints", points);
		mav.addObject("child", child);
		return mav; 
	}
	
	
	
	
	@Override
	@RequestMapping(value = "/remove/{id}")
	public ActionResult remove(@PathVariable("id")String id) {
		
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mav = new ModelAndView("remove");
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries(String.format("id`%s;teacher`%s", id,user.getUsername()));
		getService().remove(qc);
		return ActionResult.SUCCESS;
		
	}

	
	@RequestMapping(value = "/currentgrade/{page}")
	public ModelAndView currentGrade(
			@RequestParam(value = "offset", required = false, defaultValue = "10") int offset,
			@RequestParam(value = "page",required = false,defaultValue = "0") int page,
			@RequestParam(value = "eduyear", required = false, defaultValue ="0" ) int eduyear,
			@RequestParam(value = "grade", required = false, defaultValue = "0") int grade
			
			
			) {
		ModelAndView mav = new ModelAndView("query");
		if(eduyear==0) {
			eduyear = LocalDate.now().getYear();
			if(LocalDate.now().getMonthValue()>=3 && LocalDate.now().getMonthValue()<=8)
			  eduyear = LocalDate.now().getYear()-1;
		}
		Pager<Violates> pager = service.queryForCurrentGrade(grade, eduyear, page, offset);	
		mav.addObject("qstring", String.format("page=%s&offset=%s&eduyear=%s&grade=%s", page,offset,eduyear,grade));
		mav.addObject("queryInfo", String.format("%s学年,%s年级",eduyear,grade));
		mav.addObject("pager", pager);
		return mav;
	}
	
	@RequestMapping(value = "/chart/monthly/{eduyear}/{edumonth}")
	public ModelAndView monthlychart(
			@PathVariable("eduyear")int year,
			@PathVariable("edumonth")int month
			) {
		if(year==0||month==0) {
			year = LocalDate.now().getYear();
			month = LocalDate.now().getMonthValue();
		}
		ModelAndView mav = new ModelAndView("monthlychart");
		Set<ChartDataSource> ds = service.findChartDataSource(year, month);
		List<String> labels     = ds.stream().map(f->f.getGradeName()).collect(Collectors.toList());
		List<String> uppoints   = ds.stream().map(f->String.format("%.1f",f.getUppoint())).collect(Collectors.toList());
		List<String> downpoints = ds.stream().map(f->String.format("%.1f", f.getDownpoint()<0?f.getDownpoint()*-1:f.getDownpoint())).collect(Collectors.toList());

		mav.addObject("labels", Joiner.on("','").join(labels));
		mav.addObject("uppoints",Joiner.on(',').join(uppoints));
		mav.addObject("downpoints",Joiner.on(',').join(downpoints));
		return mav;
	}


	@Override
	public AbstractService<String,Violates> getService() {
		return service;
	}

}