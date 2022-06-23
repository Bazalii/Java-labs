package com.itmo.cats.repository;

import com.itmo.cats.coremodels.owner.Owner;

import java.util.List;

public interface OwnerRepository {
    Owner getById(int id);

    Owner add(Owner model);

    void update(Owner owner);

    void deleteById(int id);

    List<Owner> getAll();
}
