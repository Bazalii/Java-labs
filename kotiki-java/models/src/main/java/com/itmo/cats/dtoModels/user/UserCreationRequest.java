package com.itmo.cats.dtoModels.user;

public class UserCreationRequest {
    private final int _id;

    private final String _username;

    private final String _password;

    public UserCreationRequest(int id, String username, String password) {
        _id = id;
        _username = username;
        _password = password;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public int getId() {
        return _id;
    }
}
