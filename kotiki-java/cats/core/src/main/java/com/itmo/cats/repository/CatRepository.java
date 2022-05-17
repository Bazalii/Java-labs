package com.itmo.cats.repository;

import com.itmo.cats.coreModels.cat.Cat;

import java.util.List;

public interface CatRepository {
    Cat getById(int id);

    Cat add(Cat cat);

    void update(Cat cat);

    void deleteById(int id);

    List<Cat> getAll();

    void addFriendById(int firstCatId, int secondCatId);

    void removeFriendById(int firstCatId, int secondCatId);
}
