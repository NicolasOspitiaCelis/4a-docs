package com.car_ms.car_ms.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Entity {
    @Id
    private  String  id;
    private  String  entity;
    private  Integer capacityTurn;
    private Date lastChange;

    public Entity(String id, String entity, Integer capacityTurn, Date lastChange) {
        this.id = id;
        this.entity = entity;
        this.capacityTurn = capacityTurn;
        this.lastChange = lastChange;
    }

    public String getId() {
        return id;
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
