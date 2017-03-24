// ref: https://cwiki.apache.org/confluence/display/CXF20DOC/SpringBoot
package com.apress.spring.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring.service.LayoutImpl;

@Configuration
public class CXFConfig {
	
//	@Bean(name = Bus.DEFAULT_BUS_ID)
//	public SpringBus springBus() {
//		return new SpringBus();
//	}
//
//	@Bean
//	public LayoutImpl layout() {
//		return new LayoutImpl();
//	}
	
	@Autowired
	private Bus bus;

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new LayoutImpl());
		endpoint.publish("/layout");
//		 endpoint.getInInterceptors().add(new AuthInterceptor());
		return endpoint;
	}
}
