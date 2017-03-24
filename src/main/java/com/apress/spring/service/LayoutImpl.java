package com.apress.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@javax.jws.WebService(serviceName = "LayoutServiceImpl", portName = "LayoutImpl", 
	targetNamespace = "http://abc.webservice.com/", 
	endpointInterface = "com.apress.spring.service.Layout")
public class LayoutImpl implements Layout {
	private static final Logger LOG = LoggerFactory.getLogger(LayoutImpl.class);

	@Override
	public String sayHello(String name) {
		return "hello," + name;
	}

	@Override
	public OperationResult addLayout(String layoutName, String layoutContent) {
		LOG.info("layoutName:{}, layoutContent:{}", layoutName, layoutContent);
		if (StringUtils.isEmpty(layoutName) || StringUtils.isEmpty(layoutContent)) {
			return new OperationResult(false, "参数不能为空");
		}
		return new OperationResult(true, String.format("您的参数,name:%s,age:%s", layoutName, layoutContent));
	}
}
