package com.itmo.cats.domain.owner.service;

import com.itmo.cats.domain.owner.Owner;
import com.itmo.cats.domain.owner.OwnerCreationModel;

import java.util.List;

public interface OwnerService {
    Owner getById(int id);

    void add(OwnerCreationModel model);

    void update(Owner owner);

    void deleteById(int id);

    List<Owner> getAll();
}
