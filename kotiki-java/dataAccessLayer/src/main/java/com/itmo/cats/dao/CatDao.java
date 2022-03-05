package com.itmo.cats.dao;

import com.itmo.cats.models.Cat;

import java.util.List;

public interface CatDao {
    Cat getById(Integer id);

    void save(Cat cat);

    void update(Cat cat);

    void delete(Cat cat);

    List<Cat> getAll();
}
