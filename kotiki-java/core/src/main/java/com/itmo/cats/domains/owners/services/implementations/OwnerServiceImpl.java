package com.itmo.cats.domains.owners.services.implementations;

import com.itmo.cats.domains.owners.Owner;
import com.itmo.cats.domains.owners.OwnerCreationModel;
import com.itmo.cats.domains.owners.repositories.OwnerRepository;
import com.itmo.cats.domains.owners.services.OwnerService;
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

    public Owner getById(int id) {
        return _ownerRepository.getById(id);
    }

    public void add(OwnerCreationModel model) {
        var owner = new Owner();
        owner.name = model.name;
        owner.birthDate = model.birthDate;
        _ownerRepository.add(owner);
    }

    public void update(Owner owner) {
        _ownerRepository.update(owner);
    }

    public void deleteById(int id) {
        _ownerRepository.deleteById(id);
    }

    public List<Owner> getAll() {
        return _ownerRepository.getAll();
    }
}
