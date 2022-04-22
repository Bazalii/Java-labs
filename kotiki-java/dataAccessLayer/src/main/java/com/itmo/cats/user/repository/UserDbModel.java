package com.itmo.cats.user.repository;

import jdk.jfr.Name;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserDbModel {
    @Id
    @Column(name = "id")
    private int _id;

    @Column(name = "username")
    @Name("username")
    private String _username;

    @Column(name = "password")
    private String _password;

    @Column(name = "role")
    private String _role;

    @Column(name = "enabled")
    private Boolean _isEnabled;

    public UserDbModel() {
    }

    public UserDbModel(int id, String username, String password, String role, Boolean isEnabled) {
        _id = id;

        if (username == null)
            throw new IllegalArgumentException("Username cannot be null");
        _username = username;

        if (password == null)
            throw new IllegalArgumentException("Password cannot be null!");
        _password = password;

        if (role == null)
            throw new IllegalArgumentException("Role cannot be null!");
        _role = role;

        _isEnabled = isEnabled;
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

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public String getRole() {
        return _role;
    }

    public void setRole(String role) {
        _role = role;
    }

    public Boolean getEnabled() {
        return _isEnabled;
    }

    public void setEnabled(Boolean isEnabled) {
        _isEnabled = isEnabled;
    }
}
