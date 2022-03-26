package com.itmo.cats.owners.repositories;

import com.itmo.cats.domains.owners.Owner;
import com.itmo.cats.domains.owners.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {
    private final OwnerDao _ownerDao;

    @Autowired
    public OwnerRepositoryImpl(OwnerDao ownerRepository) {
        _ownerDao = ownerRepository;
    }

    @Override
    public Owner getById(int id) {
        var dbModel = _ownerDao.getById(id);
        return castDbModelToOwner(dbModel);
    }

    @Override
    public void add(Owner model) {
        var dbModel = new OwnerDbModel(model.getName(), model.getBirthDate());
        _ownerDao.save(dbModel);
    }

    @Override
    public void update(Owner owner) {
        var dbModel = new OwnerDbModel(owner.getName(), owner.getBirthDate());
        dbModel.setId(owner.getId());
        _ownerDao.save(dbModel);
    }

    @Override
    public void deleteById(int id) {
        _ownerDao.deleteById(id);
    }

    @Override
    public List<Owner> getAll() {
        var dbModels = _ownerDao.findAll();
        var result = new ArrayList<Owner>();
        for (OwnerDbModel dbModel : dbModels) {
            Owner owner = castDbModelToOwner(dbModel);
            result.add(owner);
        }
        return result;
    }

    private Owner castDbModelToOwner(OwnerDbModel dbModel) {
        var owner = new Owner();
        owner.setId(dbModel.getId());
        owner.setName(dbModel.getName());
        owner.setBirthDate(dbModel.getBirthDate());
        return owner;
    }
}
