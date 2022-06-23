package com.itmo.cats.coremodels.cat;

import java.io.Serializable;

public class GetCatByIdMessage implements Serializable {
    private int _id;

    private String _username;

    public GetCatByIdMessage() {

    }

    public GetCatByIdMessage(int id, String username) {
        _id = id;
        _username = username;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
    }
}
