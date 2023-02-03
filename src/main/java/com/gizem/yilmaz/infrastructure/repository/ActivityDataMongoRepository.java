package com.gizem.yilmaz.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.gizem.yilmaz.domain.Activity;
import com.gizem.yilmaz.domain.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ActivityDataMongoRepository implements ActivityRepository {

	private final ActivityMongoRepository courierActivityMongoRepository;

	public void saveActivity(final Activity courierActivity) {
		courierActivityMongoRepository.save(courierActivity);
	}

}
