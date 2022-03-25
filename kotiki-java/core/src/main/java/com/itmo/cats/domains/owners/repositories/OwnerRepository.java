package com.itmo.cats.domains.owners.repositories;

import com.itmo.cats.domains.owners.Owner;

import java.util.List;

public interface OwnerRepository {
    Owner getById(int id);

    void add(Owner model);

    void update(Owner owner);

    void deleteById(int id);

    List<Owner> getAll();
}
