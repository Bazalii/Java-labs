package com.itmo.cats.domain.cat.repository;

import com.itmo.cats.domain.cat.Cat;

import java.util.List;

public interface CatRepository {
    Cat getById(int id);

    void add(Cat cat);

    void update(Cat cat);

    void deleteById(int id);

    List<Cat> getAll();

    void addFriendById(int firstCatId, int secondCatId);

    void removeFriendById(int firstCatId, int secondCatId);
}