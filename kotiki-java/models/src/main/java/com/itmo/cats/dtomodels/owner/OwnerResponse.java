package com.itmo.cats.dtomodels.owner;

import java.util.Date;

public class OwnerResponse {
    private final int _id;

    private final String _name;

    private final Date _birthDate;

    public OwnerResponse(int id, String name, Date birthDate) {
        _id = id;
        _name = name;
        _birthDate = birthDate;
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
}
