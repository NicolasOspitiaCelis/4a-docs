package com.car_ms.car_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class NoTurnsAdvice {
    @ResponseBody
    @ExceptionHandler(NoTurnsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String NoTurnsAdvice(NoTurnsException ex){
        return ex.getMessage();
    }
}