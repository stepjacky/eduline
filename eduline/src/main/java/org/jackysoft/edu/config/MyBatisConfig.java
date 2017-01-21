package org.jackysoft.edu.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableCaching(proxyTargetClass=true)
public class MyBatisConfig {
	static final String BASE_PACKAGE="org.jackysoft.edu.mapper";
	@Bean
	public MapperScannerConfigurer MapperScannerConfigurer() {
		MapperScannerConfigurer bean = new MapperScannerConfigurer();
		bean.setBasePackage(BASE_PACKAGE);
		return bean;
	}
}
