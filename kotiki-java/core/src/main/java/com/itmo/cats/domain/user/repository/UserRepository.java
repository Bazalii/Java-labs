package com.itmo.cats.domain.user.repository;

import com.itmo.cats.domain.user.User;

public interface UserRepository {
    void add(User model);

    void deleteById(int id);
}
