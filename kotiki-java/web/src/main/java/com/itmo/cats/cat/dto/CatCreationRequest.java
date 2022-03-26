package com.itmo.cats.cat.dto;

import com.itmo.cats.domain.Color;

import java.util.Date;

public class CatCreationRequest {
    private final String _name;

    private final Date _birthDate;

    private final String _breed;

    private final Color _color;

    private final int _ownerId;

    public CatCreationRequest(String name, Date birthDate, String breed, Color color, int ownerId) {
        _name = name;
        _birthDate = birthDate;
        _breed = breed;
        _color = color;
        _ownerId = ownerId;
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
