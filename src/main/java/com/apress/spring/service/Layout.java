package com.apress.spring.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="LayoutService", targetNamespace="http://abc.webservice.com")
public interface Layout {
	@WebMethod
	String sayHello(@WebParam(name="name") String name);
	
	@WebMethod
	OperationResult addLayout(
			@WebParam(name="layoutName") String layoutName,
			@WebParam(name="layoutContent") String layoutContent);
}
