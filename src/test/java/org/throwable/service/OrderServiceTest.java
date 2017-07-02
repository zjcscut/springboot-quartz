package org.throwable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.throwable.Application;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/2 21:41
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	@Qualifier(value = "orderService2")
	private OrderService orderService2;

	@Autowired
	@Qualifier(value = "orderService3")
	private OrderService orderService3;

	@Autowired
	private DefaultListableBeanFactory beanFactory;

	@Test
	public void testOrderService()throws Exception{
		OrderService orderService = beanFactory.getBean(OrderService.class);
		System.out.println("success");
	}
}
