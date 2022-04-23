package com.itmo.cats.domain.user.service;

import com.itmo.cats.domain.user.User;
import com.itmo.cats.domain.user.UserCreationModel;

public interface UserService {
    User add(UserCreationModel model);

    void deleteById(int id);
}
