package com.itmo.cats.dao;

import com.itmo.cats.models.Owner;

import java.util.List;

public interface OwnerDao {
    Owner getById(Integer id);

    void save(Owner owner);

    void update(Owner owner);

    void delete(Owner owner);

    List<Owner> getAll();
}
