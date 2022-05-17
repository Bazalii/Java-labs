package com.itmo.cats.repository;

import com.itmo.cats.coreModels.user.User;

public interface UserRepository {
    User add(User model);

    void deleteById(int id);
}
