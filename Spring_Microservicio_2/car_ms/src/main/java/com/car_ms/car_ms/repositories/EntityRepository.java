package com.car_ms.car_ms.repositories;

import com.car_ms.car_ms.models.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntityRepository extends MongoRepository<Entity, String> {
}
