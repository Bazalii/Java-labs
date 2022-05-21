package com.itmo.cats.dtomodels.cat;

public class GetAllCatsByIdMessage {
    private final String _username;

    public GetAllCatsByIdMessage(String username) {
        _username = username;
    }

    public String getUsername() {
        return _username;
    }
}
