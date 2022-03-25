package com.itmo.cats.owners.repositories;

import com.itmo.cats.domains.owners.Owner;
import com.itmo.cats.domains.owners.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {
    private final OwnerDto _ownerDto;

    @Autowired
    public OwnerRepositoryImpl(OwnerDto ownerRepository) {
        _ownerDto = ownerRepository;
    }

    @Override
    public Owner getById(int id) {
        var dbModel = _ownerDto.getById(id);
        return CastDbModelToOwner(dbModel);
    }

    @Override
    public void add(Owner model) {
        var dbModel = new OwnerDbModel(model.name, model.birthDate);
        _ownerDto.save(dbModel);
    }

    @Override
    public void update(Owner owner) {
        var dbModel = new OwnerDbModel(owner.name, owner.birthDate);
        dbModel.setId(owner.id);
        _ownerDto.save(dbModel);
    }

    @Override
    public void deleteById(int id) {
        _ownerDto.deleteById(id);
    }

    @Override
    public List<Owner> getAll() {
        var dbModels = _ownerDto.findAll();
        var result = new ArrayList<Owner>();
        for (OwnerDbModel dbModel : dbModels) {
            Owner owner = CastDbModelToOwner(dbModel);
            result.add(owner);
        }
        return result;
    }

    private Owner CastDbModelToOwner(OwnerDbModel dbModel) {
        var owner = new Owner();
        owner.id = dbModel.getId();
        owner.name = dbModel.getName();
        owner.birthDate = dbModel.getBirthDate();
        return owner;
    }
}
