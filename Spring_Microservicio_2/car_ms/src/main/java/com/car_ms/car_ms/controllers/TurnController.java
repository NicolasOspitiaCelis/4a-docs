package com.car_ms.car_ms.controllers;

import com.car_ms.car_ms.exceptions.NoTurnsException;
import com.car_ms.car_ms.models.Turn;
import com.car_ms.car_ms.repositories.TurnRepository;
import com.car_ms.car_ms.models.Entity;
import com.car_ms.car_ms.repositories.EntityRepository;
import org.springframework.transaction.TransactionUsageException;
import  com.car_ms.car_ms.exceptions.LateTurnException;
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
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Date dt = new Date();

        calendar1.setTime(dt);
        calendar2.setTime(entity.getLastChange());


        if (calendar1.get(Calendar.HOUR_OF_DAY) >= 23 || calendar1.get(Calendar.HOUR_OF_DAY) <= 3){
          throw new LateTurnException("No hay turnos disponibles en este horario");
        }

        if (calendar2.get(Calendar.DAY_OF_YEAR) < calendar1.get(Calendar.DAY_OF_YEAR) ){
            entity.setCapacityTurn(10);
        }

        if (entity.getCapacityTurn() == 0){
            throw new NoTurnsException("No hay mas turnos disponibles");
        }
        else {
            entity.setCapacityTurn(entity.getCapacityTurn()-1);
        }

        entity.setLastChange(dt);
        turn.setTurn(10-entity.getCapacityTurn());
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
