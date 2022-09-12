package com.example.knowledge;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.knowledge.model.MyObject;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableEncryptableProperties
@EnableCaching
public class KnowledgeApplication {
		
//	@Scheduled(cron = "${scheduling.cron}")
//	public void home() {
//		System.out.println("Now is " +Instant.now());
//	}

	public static void main(String[] args) throws SchedulerException {
		JobDetail j = JobBuilder.newJob(MyObject.class).build();
		
		Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(02)
						.repeatForever()).build();
		Scheduler s = StdSchedulerFactory.getDefaultScheduler();
		s.start();
		s.scheduleJob(j, t);
		SpringApplication.run(KnowledgeApplication.class, args);
	}

}
/*
 * <dependency> <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-security</artifactId> </dependency>
 * 
 * <dependency> <groupId>org.springframework.security</groupId>
 * <artifactId>spring-security-test</artifactId> <scope>test</scope>
 * </dependency>
 * 
 * <dependency> <groupId>io.jsonwebtoken</groupId> <artifactId>jjwt</artifactId>
 * <version>${jwt.version}</version> </dependency>
 */
