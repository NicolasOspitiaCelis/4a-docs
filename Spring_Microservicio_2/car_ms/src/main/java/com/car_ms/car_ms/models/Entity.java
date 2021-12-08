package com.car_ms.car_ms.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import java.util.Date;

import java.util.Date;
public class Entity {

    @Id
    private String entity;
    private Integer capacityTurn;
    private Date lastChange;

    // for deserialisation
    public Entity() {
        this.capacityTurn = 50;
        this.lastChange = new Date();
    }

    public Entity(String entity) {
        this.entity = entity;
        this.capacityTurn = 50;
        this.lastChange = new Date();
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Integer getCapacityTurn() {
        return capacityTurn;
    }

    public void setCapacityTurn(Integer capacityTurn) {
        this.capacityTurn = capacityTurn;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }
}
