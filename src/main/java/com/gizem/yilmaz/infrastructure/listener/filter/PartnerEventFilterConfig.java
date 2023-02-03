package com.gizem.yilmaz.infrastructure.listener.filter;

import java.nio.charset.StandardCharsets;

import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import com.gizem.yilmaz.application.EventTypeToBinding;

public interface PartnerEventFilterConfig {

	public MessageRoutingCallback messageRoutingCallback();

	public default String resolveTarget(Message<?> message) {
		String target = null;
		MessageHeaders headers = message.getHeaders();
		Object typeHeader = headers.get("type");

		if (typeHeader instanceof byte[]) {
			target = new String((byte[]) typeHeader, StandardCharsets.UTF_8);
		}
		return EventTypeToBinding.findByKey(target);
	}
}
