package com.example;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.github.benmanes.caffeine.cache.Caffeine;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableMongoRepositories
@EnableSwagger2
@EnableCaching
@EnableMongoAuditing
@EnableAsync
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Bean(name="taskExecutor")
	public Executor getAsyncThreadPoolExecutor() {
		ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(20);
		taskExecutor.setMaxPoolSize(1000);
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor.setThreadNamePrefix("Async-");
		return taskExecutor;
	}
	
	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	 @Bean
	 public Caffeine caffeineConfig() {
		 
	     return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
	 }
	 @Bean
	 public CacheManager cacheManager(Caffeine caffeine) {
	     CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
	     caffeineCacheManager.setCaffeine(caffeine);
	     return caffeineCacheManager;
	 }
	 
	 @Bean
	 public AuditorAware<String> auditorAware(){
		 return new MongoAuditable();
	 }
	
}
