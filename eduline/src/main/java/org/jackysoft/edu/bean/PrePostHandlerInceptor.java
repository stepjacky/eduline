package org.jackysoft.edu.bean;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Splitter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.query.Pager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.google.common.base.Strings;

public class PrePostHandlerInceptor extends HandlerInterceptorAdapter {

	static final Log logger = LogFactory.getLog(PrePostHandlerInceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	@Override
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			ModelAndView mav)
			throws Exception {


		logger.info(request.getRequestURI()+" >> "+(mav!=null?mav.getViewName():""));
		if(mav==null){
			logger.info(request.getRequestURI()+", got null mav");
			return;
		}
		if (".jasper".equals(request.getRequestURI())) {
		    mav.setViewName(mav.getViewName().substring(1));
			return;
		}


		HandlerMethod hd = (HandlerMethod) handler;
		if (hd.getMethod().isAnnotationPresent(ResponseBody.class))return;
		String mname = hd.getMethod().getName();
		RequestMapping rm = hd.getBeanType().getAnnotation(RequestMapping.class);
		String prefix = (rm == null) ? "/" : rm.value()[0];

		Method beforeInput = hd.getBeanType().getMethod("beforeInput",ModelAndView.class);
		beforeInput.invoke(hd.getBean(),mav);
		LocalDateTime now = LocalDateTime.now();
		mav.addObject("atNow", now);
		mav.addObject("simpleNow", LocalDate.now().toString());
		mav.addObject("sysUser", SecurityContextHolder.getContext().getAuthentication());
		mav.addObject("base", request.getContextPath());

		if (!Strings.isNullOrEmpty(mav.getViewName()))
			mname = mav.getViewName();
		if (!Strings.isNullOrEmpty(mname) && !mname.contains("redirect:")) {
			if (!(mav.getView() instanceof JasperReportsPdfView)) {
				if(mname.indexOf('/')<0)
				mav.setViewName(prefix + (prefix.endsWith("/") ? "" : "/") + mname);
			}

		}

		Optional<String> qs = Optional.ofNullable(request.getQueryString());

		String fullUrl = request.getRequestURI() + "?" + qs.orElse("");
		if (mav.getModelMap().containsAttribute("qstring")
				&& "POST".equals(request.getMethod().toUpperCase())) {
			String s = mav.getModelMap().get("qstring").toString();
			fullUrl += "&" + s;

		}
		for(Object v : mav.getModel().values()){
			if(v==null) continue;
			if (v.getClass() == Pager.class) {
				Pager po = Pager.class.cast(v);
				if (po != null) {
					po.setUri(fullUrl);
				}
			}
		}
		String finalQuery = qs.orElse("");
		Iterable<String> first = Splitter.on('&').split(finalQuery);
		for(String q:first){
			List<String> paire = Splitter.on('=').splitToList(q);
			if(paire!=null && !paire.isEmpty() && paire.size()>=2){
				mav.addObject(paire.get(0),paire.get(1));
			}
		}
	}

}
