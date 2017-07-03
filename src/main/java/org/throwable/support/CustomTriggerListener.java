package org.throwable.support;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/4 0:43
 */
@Component
public class CustomTriggerListener implements TriggerListener {


	@Override
	public String getName() {
		return "customTriggerListener";
	}

	//被调度器调用时回调此方法
	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		System.out.println("triggerFired");
	}

	//triggerFired方法被调用之后调用,如果返回true那么和此Trigger关联的Job的实现里面的execute方法将不会被调用
	//也就是这个方法决定了是否执行job
	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		System.out.println("vetoJobExecution");
		return false;
	}

	//错失触发时回调此方法
	@Override
	public void triggerMisfired(Trigger trigger) {
		System.out.println("triggerMisfired");
	}

	//触发完成后回调此方法
	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
		System.out.println("triggerComplete");
	}
}
