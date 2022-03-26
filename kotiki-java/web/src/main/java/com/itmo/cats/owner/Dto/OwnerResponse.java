package com.itmo.cats.owner.Dto;

import java.util.Date;

public class OwnerResponse {
    private int _id;

    private String _name;

    private Date _birthDate;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Date getBirthDate() {
        return _birthDate;
    }

    public void setBirthDate(Date birthDate) {
        _birthDate = birthDate;
    }
}
