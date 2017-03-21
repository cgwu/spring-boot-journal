package com.apress.spring;

import java.math.BigDecimal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apress.spring.domain.Testjson2;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	private static final Logger log = LoggerFactory.getLogger(JacksonTest.class);

	@Test
	public void test() throws Exception {
		// 对象转json
		Testjson2 obj1 = new Testjson2("{\"name\":\"吴cg\",\"age\":130}", new BigDecimal("3.1415926"));
		ObjectMapper map = new ObjectMapper();
		String json = map.writeValueAsString(obj1);
		log.info(json);
		// json转对象
		Testjson2 obj2 = map.readValue(json, Testjson2.class);
		log.info(obj2.toString());
	}

}
