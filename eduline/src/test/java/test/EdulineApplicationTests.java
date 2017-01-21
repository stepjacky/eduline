package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.EdulineApplication;
import org.jackysoft.edu.config.CachedConfig;
import org.jackysoft.edu.config.DataAccessConfig;
import org.jackysoft.edu.config.ExtendedConfig;
import org.jackysoft.edu.config.MyBatisConfig;
import org.jackysoft.edu.entity.UserEvent;
import org.jackysoft.edu.service.SysUserService;
import org.jackysoft.edu.service.UserEventService;
import org.jackysoft.query.Pager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EdulineApplication.class)
public class EdulineApplicationTests {

	final Log logger = LogFactory.getLog(EdulineApplicationTests.class);
	
	@Autowired
	SysUserService userService;
		
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	protected UserEventService service;
    @Test
	public void contextLoads() throws Exception {
    	Pager<UserEvent> pager = service.myRepeatedEvents("cuizheng", 0);
    	logger.info(pager);
	}

}
