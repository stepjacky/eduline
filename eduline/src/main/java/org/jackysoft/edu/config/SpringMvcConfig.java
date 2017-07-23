package org.jackysoft.edu.config;

import org.jackysoft.edu.bean.PrePostHandlerInceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
		.addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/errorHtml")
		.addResourceLocations("/WEB-INF/views/errorHtml.jsp");
		registry.addResourceHandler("/error")
		.addResourceLocations("/WEB-INF/views/error.jsp");
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

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/",".jsp");
		super.configureViewResolvers(registry);
	}

	@Bean
	public HandlerInterceptor preHandler() {
		return new PrePostHandlerInceptor();
	}
	
}
