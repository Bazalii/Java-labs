package com.itmo.cats.repository;

import com.itmo.cats.coremodels.cat.Cat;
import com.itmo.cats.coremodels.cat.GetAllCatsByIdMessage;
import com.itmo.cats.coremodels.cat.GetCatByIdMessage;

import java.util.List;

public interface CatRepository {
    Cat getById(GetCatByIdMessage getCatByIdMessage);
    Cat add(Cat cat);
    void update(Cat cat);
    void deleteById(int id);
    List<Cat> getAll(GetAllCatsByIdMessage getAllCatsByIdMessage);
    void addFriendById(int firstCatId, int secondCatId);
    void removeFriendById(int firstCatId, int secondCatId);
}
