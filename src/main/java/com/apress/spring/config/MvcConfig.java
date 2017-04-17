// from: https://dzone.com/articles/spring-security-in-mvc-4-using-spring-boot

package com.apress.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 设定默认路程径对应视图
//		registry.addViewController("/").setViewName("default");
//		registry.addViewController("/home").setViewName("home");
//		registry.addViewController("/hello").setViewName("hello");
//		registry.addViewController("/login").setViewName("login");
	}

//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/jsp/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}

}
