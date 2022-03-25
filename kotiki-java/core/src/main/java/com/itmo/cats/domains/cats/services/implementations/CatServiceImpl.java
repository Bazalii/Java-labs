package com.itmo.cats.domains.cats.services.implementations;

import com.itmo.cats.domains.cats.Cat;
import com.itmo.cats.domains.cats.CatCreationModel;
import com.itmo.cats.domains.cats.repositories.CatRepository;
import com.itmo.cats.domains.cats.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    private final CatRepository _catRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository) {
        _catRepository = catRepository;
    }

    @Override
    public Cat getById(int id) {
        return _catRepository.getById(id);
    }

    @Override
    public void add(CatCreationModel model) {
        var cat = new Cat();
        cat.name = model.name;
        cat.birthDate = model.birthDate;
        cat.breed = model.breed;
        cat.color = model.color;
        cat.ownerId = model.ownerId;
        _catRepository.add(cat);
    }

    @Override
    public void update(Cat cat) {
        _catRepository.update(cat);
    }

    @Override
    public void deleteById(int id) {
        _catRepository.deleteById(id);
    }

    @Override
    public List<Cat> getAll() {
        return _catRepository.getAll();
    }

    @Override
    public void addFriendById(int firstCatId, int secondCatId) {
        _catRepository.addFriendById(firstCatId, secondCatId);
    }

    @Override
    public void removeFriendById(int firstCatId, int secondCatId) {
        _catRepository.removeFriendById(firstCatId, secondCatId);
    }
}
