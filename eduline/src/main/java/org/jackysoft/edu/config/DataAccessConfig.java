package org.jackysoft.edu.config;

import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement(proxyTargetClass = true)
public class DataAccessConfig {

	static final Log logger = LogFactory.getLog(DataAccessConfig.class);

	@Bean
	@ConfigurationProperties(prefix = "datasource.cp30")
	public DataSource dataSource() {
		
		ComboPooledDataSource ds = new ComboPooledDataSource(true);		
		ds.setIdleConnectionTestPeriod(18000);	
		ds.setTestConnectionOnCheckout(true);		
		return ds;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager bean = new DataSourceTransactionManager(
				dataSource());
		return bean;
	}

	@Bean
	@ConfigurationProperties(prefix = "sessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());	
		return bean;

	}

	@Bean
	public SqlSessionTemplate sessionTemplate() throws Exception {
		SqlSessionTemplate session = new SqlSessionTemplate(sqlSessionFactory()
				.getObject(), ExecutorType.BATCH);
		return session;
	}

	
}
