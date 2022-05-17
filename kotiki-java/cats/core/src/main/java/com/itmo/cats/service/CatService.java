package com.itmo.cats.service;

import com.itmo.cats.coreModels.cat.Cat;
import com.itmo.cats.coreModels.cat.CatCreationModel;

import java.util.List;

public interface CatService {
    Cat getById(int id);

    Cat add(CatCreationModel model);

    void update(Cat cat);

    void deleteById(int id);

    List<Cat> getAll();

    void addFriendById(int firstCatId, int secondCatId);

    void removeFriendById(int firstCatId, int secondCatId);
}
