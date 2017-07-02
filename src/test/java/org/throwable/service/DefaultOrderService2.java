package org.throwable.service;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/2 21:37
 */
public class DefaultOrderService2 implements OrderService {

	@Override
	public void process() {
		System.out.println("DefaultOrderService2 --> #process");
	}
}
