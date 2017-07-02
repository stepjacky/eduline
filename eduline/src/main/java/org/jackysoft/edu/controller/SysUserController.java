package org.jackysoft.edu.controller;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.lang.StringUtils;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.extend.jasperreport.JRRawDataSource;
import org.jackysoft.edu.formbean.XpConfirm;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.SysUserService;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import net.sf.jasperreports.engine.JRDataSource;


@Controller
@RequestMapping("/sysuser")
public class SysUserController extends AbstractController<String,SysUser>{

	
	
	
	@Autowired
	protected SysUserService service;
	
	
	@ResponseBody
	@RequestMapping("/exists/{name}")
	public long usercount(@PathVariable("name") String name) {
		return service.exists(name);
	}
	
	@RequestMapping("/upgrade/grade")
	public ModelAndView updategrade() {
		ModelAndView mav = new ModelAndView("update");
	    service.upgradeGrade();
		return mav;
	}
	
	
	@Autowired@Named("xpconfirm")
	protected JasperReportsPdfView xpconfirm;
	
	@RequestMapping(value = "/reporter/xpconfirm.jasper", method = RequestMethod.GET)
	public ModelAndView reporter(@RequestParam("query")String query) {
		Map<String, Object> model = new HashMap<>();
		JRDataSource ds = this.retriveJRDataSource(query);		
		model.put("datasource", ds);		
		ModelAndView mav = new ModelAndView(xpconfirm,model);
		return mav;
	}
	
	
	@Override
	JRDataSource retriveJRDataSource(String query) {
		SysUser user = service.findById(StringUtils.trim(query));
		if(null == user) new JRRawDataSource<XpConfirm>( new XpConfirm(null));
		XpConfirm bean = new XpConfirm(user);			
		JRDataSource ds = new JRRawDataSource<XpConfirm>(bean);
		return ds;
	}	
	
	@RequestMapping(value = "/xpconfirm/input")
	public ModelAndView xpinput() {
		ModelAndView mav = new ModelAndView("xpinput");
		return mav;
	}

	@ResponseBody
	@RequestMapping("/find/typed/{userType}/{page}")
	public Pager<SysUser> typedUser(
			@PathVariable("userType")String userType
			,@PathVariable("page")int page) {		
		Pager<SysUser> pager = service.findAllByUserType(page, userType);
		pager.setAjaxable(true);
		pager.setUri(String.format("/sysuser/find/typed/%s/%d", userType,page));
		
		return pager;
	}
	 
	
		
	@Override
	protected void proceedUpload(SysUser bean, Part part) throws Exception {
		service.importsUsers(part.getInputStream());
	}
	
	

	@Override
	protected
	Pager<SysUser> queryPager(String query, String group, String order, int page, int offset, boolean ajax) {
		Pager<SysUser> pager = null;
		QueryBuilder qc = new QueryBuilder();
        qc.setGroupBy(group);
        qc.setOrders(order);
		String val = qc.getFieldMap(query).get("username")+"";
		qc.setQueries(String.format("username`LIKE`%s`OR;nickname`LIKE`%s",val,val));
		pager = getService().findPager(qc, page, offset);
		pager.setAjaxable(ajax);
		return pager;
	}

	
	
	
	
	
	
	
	@Override
	protected Pager<SysUser> queryTable(String query, String group, String order, int page, int offset, boolean ajax) {
		
		return super.queryPager(query, group, order, page, offset, ajax);
	}

	@Override
	public AbstractService<String,SysUser> getService() {
	
		return service;
	}

}