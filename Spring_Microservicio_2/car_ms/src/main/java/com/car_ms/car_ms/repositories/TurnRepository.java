package com.car_ms.car_ms.repositories;

import com.car_ms.car_ms.models.Turn;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TurnRepository extends MongoRepository<Turn, String> {
    List<Turn> findByEntity (String entity);
}
