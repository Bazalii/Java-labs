package com.itmo.cats.service.implementation;

import com.itmo.cats.coreModels.owner.Owner;
import com.itmo.cats.coreModels.owner.OwnerCreationModel;
import com.itmo.cats.repository.OwnerRepository;
import com.itmo.cats.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository _ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerDao) {
        _ownerRepository = ownerDao;
    }

    @Override
    public Owner getById(int id) {
        return _ownerRepository.getById(id);
    }

    @Override
    public Owner add(OwnerCreationModel model) {
        var owner = new Owner();
        owner.setName(model.getName());
        owner.setBirthDate(model.getBirthDate());
        return _ownerRepository.add(owner);
    }

    @Override
    public void update(Owner owner) {
        _ownerRepository.update(owner);
    }

    @Override
    public void deleteById(int id) {
        _ownerRepository.deleteById(id);
    }

    @Override
    public List<Owner> getAll() {
        return _ownerRepository.getAll();
    }
}
