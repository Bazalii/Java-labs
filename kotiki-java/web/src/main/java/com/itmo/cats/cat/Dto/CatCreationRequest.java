package com.itmo.cats.cat.Dto;

import com.itmo.cats.domain.Color;

import java.util.Date;

public class CatCreationRequest {
    private String _name;

    private Date _birthDate;

    private String _breed;

    private Color _color;

    private int _ownerId;

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
        this._birthDate = birthDate;
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
