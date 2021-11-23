package com.car_ms.car_ms.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Turn {
    @Id
    private String id;
    private String entity;
    private Integer document;
    private String names;
    private String lastName;
    private String number;
    private Date date;
    private Integer turn;

    private Turn(String id, String entity, Integer document, String names, String lastName, String number, Date date, Integer turn){
        this.id = id;
        this.entity = entity;
        this.document = document;
        this.names = names;
        this.lastName = lastName;
        this.number = number;
        this.date = date;
        this.turn = turn;
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

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }
}
