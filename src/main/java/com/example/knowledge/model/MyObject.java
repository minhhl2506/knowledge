package com.example.knowledge.model;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyObject extends QuartzJobBean {
	
//	@Autowired
//	private CarRepository carRepository;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//		// TODO Auto-generated method stub
//		System.out.println("Job running!");
//		System.out.println(new Date());
////		List<Car> cars = this.carRepository.findAll();
////		for(Car car : cars) {
////			System.out.println(car.getName()+"   "); 
////		}
		
//		JobDataMap job = new JobDataMap();
//		job.put("key", "value");
//		
//		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
//		for(String key : mergedJobDataMap.getKeys()) {
//			System.out.println(key);
//		}
	}

}
