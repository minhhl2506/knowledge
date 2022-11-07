package com.example.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

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

	public static void main(String[] args) {
//		JobDetail j = JobBuilder.newJob(MyObject.class).build();
//
//		Trigger t = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(02).repeatForever()).build();
//		Scheduler s = StdSchedulerFactory.getDefaultScheduler();
//		s.start();
//		s.scheduleJob(j, t);
		SpringApplication.run(KnowledgeApplication.class, args);
	}

}
