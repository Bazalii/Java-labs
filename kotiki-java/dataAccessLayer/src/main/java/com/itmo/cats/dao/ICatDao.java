package com.itmo.cats.dao;

import com.itmo.cats.models.Cat;

import java.util.ArrayList;
import java.util.List;

public interface ICatDao {
    Cat getById(Integer id);

    void save(Cat cat);

    void update(Cat cat);

    void delete(Cat cat);

    List<Cat> getAll();
}
