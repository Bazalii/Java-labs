package com.itmo.cats.service;

import com.itmo.cats.coremodels.user.User;
import com.itmo.cats.coremodels.user.UserCreationModel;

public interface UserService {
    User add(UserCreationModel model);
    void deleteById(int id);
}
