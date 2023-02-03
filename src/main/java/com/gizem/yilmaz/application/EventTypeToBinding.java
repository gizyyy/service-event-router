package com.gizem.yilmaz.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventTypeToBinding {

	PARTNER_A_EVENT("topicA.PartnerAEvent", "partnerAReceived"),
	PARTNER_B_EVENT("topicB.PartnerBEvent", "partnerBReceived"), UNKNOWN("unknown", "filteredOutEvent");

	public final String type;
	public final String binding;

	public static String findByKey(String type) {
		EventTypeToBinding[] values = EventTypeToBinding.values();
		for (EventTypeToBinding value : values) {
			if (value.getType().equals(type)) {
				return value.getBinding();
			}
		}
		return UNKNOWN.getBinding();
	}

}