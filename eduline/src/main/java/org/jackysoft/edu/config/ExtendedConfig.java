package org.jackysoft.edu.config;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.service.ScheduledService;
import org.jackysoft.file.FileServer;
import org.jackysoft.utils.ClassMetaUtils;
import org.jackysoft.utils.IReportUtils;
import org.jackysoft.utils.JsonValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;



@Configuration
@EnableAsync
@EnableScheduling
public class ExtendedConfig implements SchedulingConfigurer,ServletContextInitializer {
	static final Log logger = LogFactory.getLog(ExtendedConfig.class);
	
	@Value("${hostname}")
	private String hostname;
	@Value("${noteDir}")
	protected String noteDir;
	
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }
    
      
    @Bean
    public ScheduledService scheduledService() {
    	ScheduledService bean = new ScheduledService();
    	return bean;
    }
    
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JsonValidator jsonValidator() {
    	return new JsonValidator();
    }

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		
	    String hbase = hostname;
		for(String s:arguments) {
			String val = s.substring(s.lastIndexOf('=')+1);
			if(s.startsWith("-Dhostname")) {
	         	   hbase = val;
	         	   logger.info(s);
	        }
			if(s.startsWith("-Dtest")){
				
				servletContext.setAttribute("testable", true);    
				logger.info(s);
			}
		} 
		System.setProperty("resbase", noteDir);
		
		servletContext.setAttribute("domain", hbase); 
  	    
		List<String> resPaths = ClassMetaUtils.getClassListByRecursion("jasperreports", ".jrxml", true);
		resPaths.forEach(f->findAndCompileJRxmls(new File(f)));
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(()->{
			logger.info("File Server starting in "+2012+"-----------------------\n\n\n\n");
			new FileServer().start("localhost", 2012);
			
			
		});
		
	}
	
	void findAndCompileJRxmls(File f){
		logger.info(f.getAbsolutePath()+" is report check in ");
		if(f.isFile()){
			
			IReportUtils.compileReportFile(f);
			//TODO 动态注册pdfView bean
		}else{
			File[] bases = f.listFiles(fn->fn.getName().endsWith(".jrxml"));
		    
			for(File bf:bases){
		    	findAndCompileJRxmls(bf);
		    }
		}
	}

}
