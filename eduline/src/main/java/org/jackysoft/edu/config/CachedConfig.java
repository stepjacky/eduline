package org.jackysoft.edu.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;


//@Configuration
//@EnableCaching(proxyTargetClass=true)
public class CachedConfig {

	 @Bean
     public CacheManager cacheManager() {
         // configure and return an implementation of Spring's CacheManager SPI
		 GuavaCacheManager cacheManager = new GuavaCacheManager();           
         return cacheManager;
     }

     @Bean
     public KeyGenerator keyGenerator() {
         // configure and return an implementation of Spring's KeyGenerator SPI
         return new SimpleKeyGenerator();
     }

}
