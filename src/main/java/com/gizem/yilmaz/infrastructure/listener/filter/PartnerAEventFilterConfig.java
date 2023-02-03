package com.gizem.yilmaz.infrastructure.listener.filter;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.FunctionProperties;
import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.cloud.function.context.config.RoutingFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.messaging.Message;

import com.mongodb.lang.Nullable;

@Configuration
public class PartnerAEventFilterConfig implements PartnerEventFilterConfig {

	@Bean
	RoutingFunction partnerAFilter(FunctionCatalog functionCatalog, FunctionProperties functionProperties,
			BeanFactory beanFactory, @Nullable MessageRoutingCallback routingCallback) {
		return new RoutingFunction(functionCatalog, functionProperties, new BeanFactoryResolver(beanFactory),
				messageRoutingCallback());
	}

	@Override
	public MessageRoutingCallback messageRoutingCallback() {
		return new MessageRoutingCallback() {
			@Override
			public String routingResult(Message<?> message) {
				return resolveTarget(message);
			}
		};
	}
}
