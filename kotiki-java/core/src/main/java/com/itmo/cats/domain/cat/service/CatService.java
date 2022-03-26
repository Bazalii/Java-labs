package com.itmo.cats.domain.cat.service;

import com.itmo.cats.domain.cat.Cat;
import com.itmo.cats.domain.cat.CatCreationModel;

import java.util.List;

public interface CatService {
    Cat getById(int id);

    void add(CatCreationModel model);

    void update(Cat cat);

    void deleteById(int id);

    List<Cat> getAll();

    void addFriendById(int firstCatId, int secondCatId);

    void removeFriendById(int firstCatId, int secondCatId);
}
