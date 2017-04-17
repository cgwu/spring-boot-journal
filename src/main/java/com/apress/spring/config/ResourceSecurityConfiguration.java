/*
package com.apress.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// ref: http://stackoverflow.com/questions/30536938/how-to-implement-spring-security-4-with-both-xml-and-java-config
//@Import({ SecurityConfig.class})	// 导入设置类
//@ImportResource( {"classpath:web-context.xml","classpath:service-context.xml","classpath:data-context.xml"})

@Configuration
@EnableGlobalAuthentication
@ImportResource({"classpath:applicationContext-security.xml"})
public class ResourceSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/foo/**").authenticated() // 需要授权: for test.
		.antMatchers("/","/*").permitAll()	// 允许
//		.antMatchers("/*").authenticated()		// 需要授权
		.antMatchers("/api/**").authenticated() // 需要授权
		
		.and()
//		.formLogin();
		.httpBasic();
	}
	
//	@Override
//	 public void configure(WebSecurity web) throws Exception {
//	        web
//	          .ignoring()
//	             .antMatchers("/resources/**"); // #3
//	    }
	 
}
*/
