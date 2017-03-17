package com.apress.spring.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.apress.spring.redis.Consumer;

/*
Redis (REmote DIctionary Server) is a NoSQL key-value store database. It’s written in
C and even though has a small footprint in its core, it’s very reliable, scalable, powerful, and super fast. Its
primary function is to store data structures like Lists, hashes, strings, sets, and sorted sets. One of the other
main features is that it provides a publish/subscribe messaging system, which is why you are going to use
Redis only as message broker.
*/
@Configuration
public class RedisConfig {
	
	@Value("${topic}")
	String topic;

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic(topic));
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Consumer consumer) {
		return new MessageListenerAdapter(consumer, "messageHandler");
	}

}
