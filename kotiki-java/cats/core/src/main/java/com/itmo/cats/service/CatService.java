package com.itmo.cats.service;

import com.itmo.cats.coreModels.cat.Cat;
import com.itmo.cats.coreModels.cat.CatCreationModel;
import com.itmo.cats.coreModels.cat.FriendModel;
import com.itmo.cats.dtoModels.cat.GetAllCatsByIdMessage;
import com.itmo.cats.dtoModels.cat.GetCatByIdMessage;

import java.util.List;

public interface CatService {
    Cat getById(GetCatByIdMessage getCatByIdMessage);

    Cat add(CatCreationModel model);

    void update(Cat cat);

    void deleteById(int id);

    List<Cat> getAll(GetAllCatsByIdMessage getAllCatsByIdMessage);

    void addFriendById(FriendModel friendModel);

    void removeFriendById(FriendModel friendModel);
}
