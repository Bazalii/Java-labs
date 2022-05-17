package com.itmo.cats.service;

import com.itmo.cats.coreModels.user.User;
import com.itmo.cats.coreModels.user.UserCreationModel;

public interface UserService {
    User add(UserCreationModel model);

    void deleteById(int id);
}
