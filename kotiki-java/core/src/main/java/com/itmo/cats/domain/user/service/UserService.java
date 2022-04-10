package com.itmo.cats.domain.user.service;

import com.itmo.cats.domain.user.UserCreationModel;

public interface UserService {
    void add(UserCreationModel model);

    void deleteById(int id);
}
