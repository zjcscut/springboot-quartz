package org.throwable.configuration;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.throwable.dao.CustomDao;
import org.throwable.support.CustomQuartzJob;

import java.util.HashMap;
import java.util.Map;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/3 0:49
 */
@Configuration
public class QuartzSchedulerConfiguration {


	@Bean
	public JobDetailFactoryBean jobDetail(CustomDao customDao) {
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setJobClass(CustomQuartzJob.class);
		Map<String, Object> jobDataAsMap = new HashMap<>();
		jobDataAsMap.put("customDao", customDao); //注入dao
		jobDataAsMap.put("timeout", 0);
		jobDetail.setJobDataAsMap(jobDataAsMap);
		return jobDetail;
	}

	@Bean
	public SimpleTriggerFactoryBean simpleTrigger(JobDetail jobDetail) {
		SimpleTriggerFactoryBean simpleTrigger = new SimpleTriggerFactoryBean();
		simpleTrigger.setJobDetail(jobDetail);
		simpleTrigger.setStartDelay(0);  //调度工厂实例化后，经过0秒开始执行调度
		simpleTrigger.setRepeatInterval(5000); //5秒执行一次
		return simpleTrigger;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactory(SimpleTrigger simpleTrigger) {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setTriggers(simpleTrigger);
		return schedulerFactory;
	}

}
