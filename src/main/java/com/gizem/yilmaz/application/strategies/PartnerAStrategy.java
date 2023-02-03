package com.gizem.yilmaz.application.strategies;

import org.springframework.stereotype.Service;

import com.gizem.yilmaz.application.EventTypeToBinding;
import com.gizem.yilmaz.domain.Activity;
import com.gizem.yilmaz.domain.ActivityRepository;
import com.gizem.yilmaz.infrastructure.messaging.PartnerAEvent;

import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerAStrategy implements PartnerStrategy {

	private final ActivityRepository activityRepository;

	@Override
	public boolean accepts(EventTypeToBinding eventTypeToBinding) {
		if (eventTypeToBinding == EventTypeToBinding.PARTNER_A_EVENT)
			return true;

		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public CloudEvent process(final CloudEvent cloudEvent) {
		if (cloudEvent.getData().isEmpty()) {
			return cloudEvent;
		}
		PartnerAEvent partnerAEvent = (PartnerAEvent) cloudEvent.getData().get();
		// some work....
		activityRepository.saveActivity(new Activity());
		return cloudEvent;

	}

}
