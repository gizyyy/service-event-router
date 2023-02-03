package com.gizem.yilmaz.application.strategies;

import com.gizem.yilmaz.application.EventTypeToBinding;

import io.cloudevents.CloudEvent;

public interface PartnerStrategy {

	public boolean accepts(final EventTypeToBinding eventTypeToBinding);

	@SuppressWarnings("rawtypes")
	public CloudEvent process(CloudEvent cloudEvent);

}
