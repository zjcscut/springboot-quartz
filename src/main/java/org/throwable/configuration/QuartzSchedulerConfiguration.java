package org.throwable.configuration;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
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
//		jobDetail.setDurability(true); //如果job不定义触发器立即触发,需要指定为持久化,这种job暂时不知道有什么用
		return jobDetail;
	}

	@Bean
	public SimpleTriggerFactoryBean simpleTrigger(JobDetail jobDetail) {
		SimpleTriggerFactoryBean simpleTrigger = new SimpleTriggerFactoryBean();
		simpleTrigger.setJobDetail(jobDetail);
		simpleTrigger.setStartDelay(0);  //调度工厂实例化后，经过0秒开始执行调度
		simpleTrigger.setRepeatInterval(10000); //10秒执行一次
		return simpleTrigger;
	}

	@Bean
	public SchedulerFactoryBean scheduler() {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setApplicationContextSchedulerContextKey("applicationContext");
		schedulerFactory.setAutoStartup(true); //容器启动完毕自动启动调度器
		schedulerFactory.setStartupDelay(5);  //容器启动完毕延时X秒启动调度器
		schedulerFactory.setOverwriteExistingJobs(true); //true:覆盖数据库中的job,要自行重新装载;false:以数据库中已经存在的job为准
//		schedulerFactory.setConfigLocation(new ClassPathResource("location")); //设置quartz.properties资源,demo暂时用内存,不需设置
//		schedulerFactory.setDataSource();   //设置数据源
		return schedulerFactory;
	}

	@Bean
	public CustomDynamicScheduler dynamicScheduler(Scheduler scheduler){
		CustomDynamicScheduler dynamicScheduler = new CustomDynamicScheduler();
		dynamicScheduler.setScheduler(scheduler);
		return dynamicScheduler;
	}

}
