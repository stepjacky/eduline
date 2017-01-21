package org.jackysoft.edu.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

@Configuration
public class JasperReportConfig {

	
	@Bean(name="scorepaper")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public JasperReportsPdfView scorepaper(){
		JasperReportsPdfView view = new JasperReportsPdfView();	
		view.setUrl("classpath:/jasperreports/scorepaper.jasper");
		return view;
	}
	
	
	@Bean(name="scorereport")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public JasperReportsPdfView scorereport(){
		JasperReportsPdfView view = new JasperReportsPdfView();	
		view.setUrl("classpath:/jasperreports/scorereport.jasper");
		return view;
	}
	
	@Bean(name="xpconfirm")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public JasperReportsPdfView xpconfirm(){
		JasperReportsPdfView view = new JasperReportsPdfView();	
		view.setUrl("classpath:/jasperreports/xpconfirm.jasper");
		return view;
	}
	
	@Bean(name="userviolate")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public JasperReportsPdfView userviolate(){
		JasperReportsPdfView view = new JasperReportsPdfView();	
		view.setUrl("classpath:/jasperreports/userviolate.jasper");
		return view;
	}
	
}
