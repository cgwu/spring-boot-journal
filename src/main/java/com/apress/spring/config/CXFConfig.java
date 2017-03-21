/*
package com.apress.spring.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring.service.LayoutImpl;

@Configuration
public class CXFConfig {
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public LayoutImpl layout() {
		return new LayoutImpl();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), layout());
		endpoint.publish("/layout");
//		 endpoint.getInInterceptors().add(new AuthInterceptor());
		return endpoint;
	}
}
*/
