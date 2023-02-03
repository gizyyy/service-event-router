package com.gizem.yilmaz.infrastructure.listener;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.gizem.yilmaz.application.EventTypeToBinding;
import com.gizem.yilmaz.application.strategies.PartnerStrategy;
import com.gizem.yilmaz.infrastructure.listener.converter.CloudEventConverter;

import io.cloudevents.CloudEvent;
import io.cloudevents.v1.AttributesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PartnerEventListener {

	private final List<PartnerStrategy> strategies;

	@Bean
	public Consumer<Message<?>> filteredOutEvent() {
		return message -> log.warn("Some event that we are not interested");
	}

	@Bean
	public CloudEventConverter cloudEventMessageConverter() {
		return new CloudEventConverter();
	}

	@Bean
	public Consumer<CloudEvent<AttributesImpl, ?>> partnerAReceived() {
		return cloudEvent -> {
			log.trace("Received from partner A {}", cloudEvent);
			strategies.stream().filter(s -> s.accepts(EventTypeToBinding.PARTNER_A_EVENT))
					.map(p -> p.process(cloudEvent));
		};
	}

	@Bean
	public Consumer<CloudEvent<AttributesImpl, ?>> partnerBReceived() {
		return cloudEvent -> {
			log.trace("Received from partner B{}", cloudEvent);
			strategies.stream().filter(s -> s.accepts(EventTypeToBinding.PARTNER_B_EVENT))
					.map(p -> p.process(cloudEvent));
		};

	}

}
