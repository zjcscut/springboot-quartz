package org.throwable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.throwable.configuration.MultiImplementsRegistrar;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/2 21:35
 */
@SpringBootApplication
@Import(value = MultiImplementsRegistrar.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}