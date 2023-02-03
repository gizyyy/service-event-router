package com.gizem.yilmaz.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gizem.yilmaz.domain.Activity;

public interface ActivityMongoRepository extends MongoRepository<Activity, Long> {

}
