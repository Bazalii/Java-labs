package com.itmo.cats.dtoModels.cat;

import com.itmo.cats.coreModels.Color;

import java.util.Date;

public class CatUpdateRequest {
    private final int _id;

    private final String _name;

    private final Date _birthDate;

    private final String _breed;

    private final Color _color;

    private final int _ownerId;

    public CatUpdateRequest(int id, String name, Date birthDate, String breed, Color color, int ownerId) {
        _id = id;
        _name = name;
        _birthDate = birthDate;
        _breed = breed;
        _color = color;
        _ownerId = ownerId;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public Date getBirthDate() {
        return _birthDate;
    }

    public String getBreed() {
        return _breed;
    }

    public Color getColor() {
        return _color;
    }

    public int getOwnerId() {
        return _ownerId;
    }
}
