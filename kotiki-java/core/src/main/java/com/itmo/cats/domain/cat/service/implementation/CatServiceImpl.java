package com.itmo.cats.domain.cat.service.implementation;

import com.itmo.cats.domain.cat.Cat;
import com.itmo.cats.domain.cat.CatCreationModel;
import com.itmo.cats.domain.cat.repository.CatRepository;
import com.itmo.cats.domain.cat.service.CatService;
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
        cat.setName(model.getName());
        cat.setBirthDate(model.getBirthDate());
        cat.setBreed(model.getBreed());
        cat.setColor(model.getColor());
        cat.setOwnerId(model.getOwnerId());
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
