package org.throwable.support;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/4 0:36
 */
@Component
public class CustomJobListener implements JobListener {

	@Override
	public String getName() {
		return "customJobListener";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		JobDetail jobDetail = context.getJobDetail();
		System.out.println(String.format("jobToBeExecuted >>>>>>>>> jobDetail:%s",jobDetail));
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		JobDetail jobDetail = context.getJobDetail();
		System.out.println(String.format("jobExecutionVetoed >>>>>>>>> jobDetail:%s",jobDetail));
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		JobDetail jobDetail = context.getJobDetail();
		System.out.println(String.format("jobWasExecuted >>>>>>>>> jobDetail:%s",jobDetail));
	}
}
