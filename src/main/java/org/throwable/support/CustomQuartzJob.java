package org.throwable.support;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.throwable.dao.CustomDao;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/3 0:50
 */
public class CustomQuartzJob extends QuartzJobBean {

	private CustomDao customDao;
	private int timeout;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Execute CustomQuartzJob!");
		System.out.println(customDao.select());
		System.out.println("timeout --> " + this.timeout);
	}

	public void setCustomDao(CustomDao customDao) {
		this.customDao = customDao;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
