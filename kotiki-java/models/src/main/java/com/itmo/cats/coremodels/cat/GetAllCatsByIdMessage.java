package com.itmo.cats.coremodels.cat;

import java.io.Serializable;

public class GetAllCatsByIdMessage implements Serializable {

    private String _username;

    public GetAllCatsByIdMessage() {

    }

    public GetAllCatsByIdMessage(String username) {
        _username = username;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
    }
}
