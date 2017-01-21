package org.jackysoft.edu.config;

import org.jackysoft.edu.bean.PrePostHandlerInceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
		.addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/errorHtml")
		.addResourceLocations("classpath:/jetx/errorHtml.jsp");
		registry.addResourceHandler("/error")
		.addResourceLocations("classpath:/jetx/error.jsp");
		super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(preHandler());
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		
	}

	@Bean
	public HandlerInterceptor preHandler() {
		return new PrePostHandlerInceptor();
	}
	
}
