package com.car_ms.car_ms.controllers;

import com.car_ms.car_ms.models.Entity;
import com.car_ms.car_ms.repositories.EntityRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class EntityController {
    private final EntityRepository entityRepository;

    public EntityController(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @PostMapping("/entities")
    Entity newEntitiy(@RequestBody Entity entity) {
        return entityRepository.save(entity);
    }
}