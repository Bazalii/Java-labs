package com.itmo.cats.coremodels.cat;

import com.itmo.cats.coremodels.Color;

import java.io.Serializable;
import java.util.Date;

public class CatCreationModel implements Serializable {
    private String _name;
    private Date _birthDate;
    private String _breed;
    private Color _color;
    private int _ownerId;

    public CatCreationModel() {

    }

    public CatCreationModel(String name, Date birthDate, String breed, Color color, int ownerId) {
        _name = name;
        _birthDate = birthDate;
        _breed = breed;
        _color = color;
        _ownerId = ownerId;
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
