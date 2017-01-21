package org.jackysoft.edu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;





public class ScheduledService {
	
		
	static final Log logger  = LogFactory.getLog(ScheduledService.class);
	@Scheduled(cron="0 0 23 * * *" )
	public void doUpdateScore() throws Exception{
		logger.info("update examscore statics");
		//mapper.updateScoreState();
		logger.info("update examscore statics done");		
	}
}
