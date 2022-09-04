package com.itmo.cats.dtomodels.owner;

import java.util.Date;

public class OwnerCreationRequest {

    private final String _name;
    private final Date _birthDate;

    public OwnerCreationRequest(String name, Date birthDate) {
        _name = name;
        _birthDate = birthDate;
    }

    public String getName() {
        return _name;
    }

    public Date getBirthDate() {
        return _birthDate;
    }
}
