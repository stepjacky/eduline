package org.jackysoft.aspectj;

import org.springframework.util.StringUtils;

public aspect BeanAop {
 pointcut getString(): execution( public String org.jackysoft.edu.entity.*.get*());
		
	 
	 
	 String around(): getString() {
		 
		 String raw = proceed();
		 if(raw==null)return "";
		 return StringUtils.trimAllWhitespace(raw);
	 }
}
