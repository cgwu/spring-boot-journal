package com.apress.spring.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("redisConsumer")
public class Consumer {
	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	public void messageHandler(String message) {
		log.info("消费Redis> " + message);
	}
}
