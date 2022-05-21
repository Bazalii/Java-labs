package com.itmo.cats.coreModels.user;

import com.itmo.cats.coreModels.Role;

public class User {
    private final int _id;

    private final String _username;

    private final String _password;

    private final Role _role;

    private final Boolean _isEnabled;

    public User(int id, String username, String password, Role role, Boolean isEnabled) {
        _id = id;
        _username = username;
        _password = password;
        _role = role;
        _isEnabled = isEnabled;
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

    public Role getRole() {
        return _role;
    }

    public Boolean getIsEnabled() {
        return _isEnabled;
    }
}
