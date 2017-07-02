package org.throwable.configuration;

import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.throwable.service.DefaultOrderService1;
import org.throwable.service.DefaultOrderService2;
import org.throwable.service.DefaultOrderService3;
import org.throwable.service.OrderService;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/2 21:33
 */
public class MultiImplementsRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
										BeanDefinitionRegistry registry) {
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) registry;
		BeanDefinitionBuilder builder =
				BeanDefinitionBuilder.genericBeanDefinition(DefaultOrderService1.class);
		//可以为bean设置属性等,如BeanDefinitionBuilder.addPropertyValue
		AbstractBeanDefinition beanDefinition = builder.getRawBeanDefinition();
		beanDefinition.setPrimary(true);
		BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);

		OrderService orderService2 = new DefaultOrderService2();
		beanFactory.registerSingleton("orderService2", orderService2);

		OrderService orderService3 = new DefaultOrderService3();
		beanFactory.registerSingleton("orderService3", orderService3);
	}
}
