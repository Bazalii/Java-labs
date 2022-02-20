package com.itmo.cats.Dao;

import com.itmo.cats.Models.Cat;

import java.util.ArrayList;

public interface ICatDao {
    Cat getById(Integer id);

    void save(Cat cat);

    void update(Cat cat);

    void delete(Cat cat);

    ArrayList<Cat> getAll();
}
