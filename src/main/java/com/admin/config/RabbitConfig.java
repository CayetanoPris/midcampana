package com.admin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {

	public static final String QUEUE_NAME = "test.queue";
	public static final String EXCHANGE_NAME = "test.exchange";
	public static final String ROUTING_KEY = "test.routing.key";

	@Bean
	@Primary
	public Queue queue1() {
		return new Queue("reporteCampania", false);
	}

	@Bean
	public Queue queue2() {
		return new Queue("solitudesReportes", false);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

//	@Bean
//	public MessageConverter jsonToMapMessageConverter() {
//		DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
//		defaultClassMapper.setTrustedPackages("com.admin.dto"); // trusted packages
//		Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
//		jackson2JsonMessageConverter.setClassMapper(defaultClassMapper);
//		return jackson2JsonMessageConverter;
//	}
}
