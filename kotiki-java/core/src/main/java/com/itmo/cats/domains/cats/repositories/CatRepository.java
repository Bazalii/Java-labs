package com.itmo.cats.domains.cats.repositories;

import com.itmo.cats.domains.cats.Cat;
import org.springframework.stereotype.Repository;

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
