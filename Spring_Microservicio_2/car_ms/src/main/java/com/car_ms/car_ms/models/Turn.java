package com.car_ms.car_ms.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Turn {
    @Id
    private String id;
    private String entity;
    private Long document;
    private String names;
    private String lastName;
    private String phone;
    private Date date;
    private Integer turn;

    private Turn(String id, String entity, Long document, String names, String lastName, String phone){
        this.id = id;
        this.entity = entity;
        this.document = document;
        this.names = names;
        this.lastName = lastName;
        this.phone = phone;
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

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
