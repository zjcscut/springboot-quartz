package org.throwable.configuration;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.throwable.support.CustomJobListener;
import org.throwable.support.CustomTriggerListener;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/3 23:41
 */
@Slf4j
public class CustomDynamicScheduler implements InitializingBean, ApplicationContextAware {

	private Scheduler scheduler;
	private ApplicationContext applicationContext;

	@Autowired
	private JobDetail jobDetail;

	@Autowired
	private SimpleTrigger simpleTrigger;

	@Autowired
	private CustomJobListener customJobListener;

	@Autowired
	private CustomTriggerListener customTriggerListener;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(scheduler, "Scheduler must not be null!");
		if (log.isDebugEnabled()) {
			log.debug(">>>>>>>>> init quartz scheduler success.[{}]", scheduler);
		}
		addJobListener();
		addTriggerListener();
		triggerExistJob();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void addTriggerListener()throws Exception{
		scheduler.getListenerManager().addTriggerListener(customTriggerListener);
	}

	public void addJobListener() throws Exception{
		scheduler.getListenerManager().addJobListener(customJobListener);
	}

	public void triggerExistJob() throws Exception{
		scheduler.scheduleJob(jobDetail, simpleTrigger);
	}


	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
}
