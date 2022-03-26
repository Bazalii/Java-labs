package com.itmo.cats.domain.cat;

import com.itmo.cats.domain.Color;

import java.util.Date;

public class Cat {
    private int _id;

    private String _name;

    private Date _birthDate;

    private String _breed;

    private Color _color;

    private int _ownerId;

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

    public String getBreed() {
        return _breed;
    }

    public void setBreed(String breed) {
        _breed = breed;
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        _color = color;
    }

    public int getOwnerId() {
        return _ownerId;
    }

    public void setOwnerId(int ownerId) {
        _ownerId = ownerId;
    }
}
