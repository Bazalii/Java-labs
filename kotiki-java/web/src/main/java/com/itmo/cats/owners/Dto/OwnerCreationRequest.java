package com.itmo.cats.owners.Dto;

import java.util.Date;

public class OwnerCreationRequest {
    private String _name;

    private Date _birthDate;

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
