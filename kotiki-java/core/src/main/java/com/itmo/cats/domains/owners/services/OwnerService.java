package com.itmo.cats.domains.owners.services;

import com.itmo.cats.domains.owners.Owner;
import com.itmo.cats.domains.owners.OwnerCreationModel;

import java.util.List;

public interface OwnerService {
    Owner getById(int id);

    void add(OwnerCreationModel model);

    void update(Owner owner);

    void deleteById(int id);

    List<Owner> getAll();
}
