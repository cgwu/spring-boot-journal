package com.apress.spring.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component("wsProducer")
public class Producer {
	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SimpMessagingTemplate template;

	public void sendMessageTo(String topic, String message) {
		log.info("产生消息,topic:{},msg:{}", topic, message);
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(dateFormatter.format(new Date()));
		builder.append("]");
		builder.append(message);
		this.template.convertAndSend("/topic/" + topic, builder.toString());
	}
}
