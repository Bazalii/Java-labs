package com.itmo.cats.dao;

import com.itmo.cats.models.Owner;

import java.util.ArrayList;

public interface IOwnerDao {
    Owner getById(Integer id);

    void save(Owner owner);

    void update(Owner owner);

    void delete(Owner owner);

    ArrayList<Owner> getAll();
}
