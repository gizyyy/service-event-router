package com.gizem.yilmaz.infrastructure.listener.converter;

import java.util.Arrays;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.json.Json;

public class CloudEventConverter extends AbstractMessageConverter {

	private final ObjectMapper objectMapper;

	public CloudEventConverter() {
		super(Arrays.asList(new MimeType("application", "vnd.cloudevent+json"), new MimeType("application", "json"),
				new MimeType("application", "cloudevents+json")));

		objectMapper = Json.MAPPER.copy();
		Json.MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Json.MAPPER.addMixIn(io.cloudevents.v1.CloudEventImpl.class, CloudEventMixIn.class);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return CloudEvent.class.isAssignableFrom(clazz);
	}

	@Override
	public Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
		Object payload = message.getPayload();
		String json = payload instanceof String ? (String) payload : new String((byte[]) payload);
		return Json.decodeValue(json, io.cloudevents.v1.CloudEventImpl.class, Object.class);
	}

	@Override
	protected Object convertToInternal(Object payload, MessageHeaders headers, Object conversionHint) {
		try {
			return objectMapper.writeValueAsBytes(payload);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("Failed to encode as JSON: " + e.getMessage());
		}
	}

}
