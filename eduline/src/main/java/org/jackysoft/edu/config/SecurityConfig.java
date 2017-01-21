package org.jackysoft.edu.config;

import org.jackysoft.edu.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(jsr250Enabled=true,securedEnabled=true,prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter 
implements ApplicationListener<AbstractAuthenticationEvent> {
	    
	@Autowired
	protected SysUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(
            		"/static/css/**",
            		"/static/scripts/**",
            		"/static/images/**",
            		"/static/lib/**",
            		"/favorate.ico")
            .permitAll()
            .antMatchers("/home").hasAnyRole("USER")          
            .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/errorlogin")
                .permitAll()
                .and()
            .logout()
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(false)
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
            .permitAll();
        super.configure(http);
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    
		
		auth.authenticationProvider(userService);
		
	
	}
	
	
		
	
	@Bean
	public PasswordEncoder passwordEncoder(){	
		return new BCryptPasswordEncoder();
	}

	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent event) {
		   logger.debug(event.toString()+" is occured ");
	}
	

}
