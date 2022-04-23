package com.itmo.cats.domain.owner.repository;

import com.itmo.cats.domain.owner.Owner;

import java.util.List;

public interface OwnerRepository {
    Owner getById(int id);

    Owner add(Owner model);

    void update(Owner owner);

    void deleteById(int id);

    List<Owner> getAll();
}
