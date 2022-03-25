package com.itmo.cats.domains.cats.services;

import com.itmo.cats.domains.cats.Cat;
import com.itmo.cats.domains.cats.CatCreationModel;

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
