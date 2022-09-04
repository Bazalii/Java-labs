package com.itmo.cats.coremodels.owner;

import java.io.Serializable;
import java.util.Date;

public class OwnerCreationModel implements Serializable {

    private final String _name;
    private final Date _birthDate;

    public OwnerCreationModel(String name, Date birthDate) {
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
