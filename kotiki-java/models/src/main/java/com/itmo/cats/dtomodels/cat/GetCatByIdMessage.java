package com.itmo.cats.dtomodels.cat;

public class GetCatByIdMessage {
    private final int _id;

    private final String _username;

    public GetCatByIdMessage(int id, String username) {
        _id = id;
        _username = username;
    }

    public int getId() {
        return _id;
    }

    public String getUsername() {
        return _username;
    }
}
