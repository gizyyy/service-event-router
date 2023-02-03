package com.gizem.yilmaz.infrastructure.listener.converter;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gizem.yilmaz.infrastructure.messaging.PartnerAEvent;
import com.gizem.yilmaz.infrastructure.messaging.PartnerBEvent;

public interface CloudEventMixIn<T> {
	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type", defaultImpl = Void.class)
	@JsonSubTypes({
			@JsonSubTypes.Type(value = PartnerAEvent.class, name = "topicA.PartnerAEvent"),
			@JsonSubTypes.Type(value = PartnerBEvent.class, name = "topicB.PartnerBEvent")

	})
	T getData();

	@JsonTypeId
	String getType();
}