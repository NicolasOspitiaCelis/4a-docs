package com.car_ms.car_ms.controllers;

import com.car_ms.car_ms.models.Turn;
import com.car_ms.car_ms.repositories.TurnRepository;
import com.car_ms.car_ms.models.Entity;
import com.car_ms.car_ms.repositories.EntityRepository;
import org.springframework.transaction.TransactionUsageException;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Calendar;

@RestController
public class TurnController {
    private final TurnRepository turnRepository;
    private final EntityRepository entityRepository;

    public TurnController(TurnRepository turnRepository,
                          EntityRepository entityRepository){
        this.turnRepository = turnRepository;
        this.entityRepository = entityRepository;
    }

    @PostMapping("/turn")
    Turn newTurn(@RequestBody Turn turn){
        Entity entity = entityRepository.findById(turn.getEntity()).orElse(null);
        Calendar c = Calendar.getInstance();
        Date dt = new Date();

        if (entity.getLastChange().compareTo(dt) <= 0){
            entity.setCapacityTurn(17);
            c.setTime(dt);
            c.add(Calendar.DATE, 1);
            c.set(Calendar.HOUR_OF_DAY, 3);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            dt = c.getTime();

        }
        else if (entity.getCapacityTurn() == 0){
            dt = entity.getLastChange();
            c.setTime(dt);
            c.add(Calendar.DATE, 1);
            c.set(Calendar.HOUR_OF_DAY, 3);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            dt = c.getTime();
            entity.setCapacityTurn(17);
        }
        else {
            entity.setCapacityTurn(entity.getCapacityTurn()-1);
            dt = entity.getLastChange();
            c.setTime(dt);
            c.add(Calendar.MINUTE, 30);
            dt = c.getTime();
        }

        entity.setLastChange(dt);
        turn.setTurn(18-entity.getCapacityTurn());
        turn.setDate(dt);

        entityRepository.save(entity);
        return turnRepository.save(turn);
    }

    @GetMapping("/turnsList")
    List<Turn> getTurnList(){
        List<Turn> turnList = turnRepository.findAll();

        return turnList;
    }

}
