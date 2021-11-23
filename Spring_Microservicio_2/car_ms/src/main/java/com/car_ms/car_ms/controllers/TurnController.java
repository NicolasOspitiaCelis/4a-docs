package com.car_ms.car_ms.controllers;

import com.car_ms.car_ms.models.Turn;
import com.car_ms.car_ms.repositories.TurnRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TurnController {
    private final TurnRepository turnRepository;

    public TurnController(TurnRepository turnRepository){
        this.turnRepository = turnRepository;
    }


}
