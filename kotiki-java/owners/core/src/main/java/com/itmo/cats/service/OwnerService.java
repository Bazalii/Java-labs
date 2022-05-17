package com.itmo.cats.service;


import com.itmo.cats.coreModels.owner.Owner;
import com.itmo.cats.coreModels.owner.OwnerCreationModel;

import java.util.List;

public interface OwnerService {
    Owner getById(int id);

    Owner add(OwnerCreationModel model);

    void update(Owner owner);

    void deleteById(int id);

    List<Owner> getAll();
}
