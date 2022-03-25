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

    public Cat getById(int id) {
        return _catRepository.getById(id);
    }

    public void add(CatCreationModel model) {
        var cat = new Cat();
        cat.name = model.name;
        cat.birthDate = model.birthDate;
        cat.breed = model.breed;
        cat.color = model.color;
        cat.ownerId = model.ownerId;
        _catRepository.add(cat);
    }

    public void update(Cat cat) {
        _catRepository.update(cat);
    }

    public void deleteById(int id) {
        _catRepository.deleteById(id);
    }

    public List<Cat> getAll() {
        return _catRepository.getAll();
    }

    public void addFriendById(int firstCatId, int secondCatId) {
        _catRepository.addFriendById(firstCatId, secondCatId);
    }

    public void removeFriendById(int firstCatId, int secondCatId) {
        _catRepository.removeFriendById(firstCatId, secondCatId);
    }
}
