package com.itmo.cats.cat.repository;

import com.itmo.cats.domain.cat.Cat;
import com.itmo.cats.domain.cat.repository.CatRepository;
import com.itmo.cats.owner.repository.OwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CatRepositoryImpl implements CatRepository {
    private final CatDao _catDao;

    private final OwnerDao _ownersDto;

    @Autowired
    public CatRepositoryImpl(CatDao catDao, OwnerDao ownersDto) {
        _catDao = catDao;
        _ownersDto = ownersDto;
    }

    @Override
    public Cat getById(int id) {
        var dbModel = _catDao.getById(id);
        return castDbModelToCat(dbModel);
    }

    @Override
    public void add(Cat cat) {
        var dbModel = new CatDbModel(cat.getName(),
                cat.getBirthDate(),
                cat.getBreed(),
                cat.getColor(),
                _ownersDto.getById(cat.getOwnerId()));
        _catDao.save(dbModel);
    }

    @Override
    public void update(Cat cat) {
        var dbModel = new CatDbModel(cat.getName(),
                cat.getBirthDate(),
                cat.getBreed(),
                cat.getColor(),
                _ownersDto.getById(cat.getOwnerId()));
        dbModel.setId(cat.getId());
        _catDao.save(dbModel);
    }

    @Override
    public void deleteById(int id) {
        _catDao.deleteById(id);
    }

    @Override
    public List<Cat> getAll() {
        var dbModels = _catDao.findAll();
        var result = new ArrayList<Cat>();
        for (CatDbModel dbModel : dbModels) {
            Cat cat = castDbModelToCat(dbModel);
            result.add(cat);
        }
        return result;
    }

    @Override
    public void addFriendById(int firstCatId, int secondCatId) {
        var cat = _catDao.getById(firstCatId);
        cat.addFriend(_catDao.getById(secondCatId));
        _catDao.save(cat);
    }

    @Override
    public void removeFriendById(int firstCatId, int secondCatId) {
        var cat = _catDao.getById(firstCatId);
        cat.removeFriend(secondCatId);
        _catDao.save(cat);
    }

    private Cat castDbModelToCat(CatDbModel dbModel) {
        var cat = new Cat();
        cat.setId(dbModel.getId());
        cat.setName(dbModel.getName());
        cat.setBirthDate(dbModel.getBirthDate());
        cat.setBreed(dbModel.getBreed());
        cat.setColor(dbModel.getColor());
        cat.setOwnerId(dbModel.getOwnerId());
        return cat;
    }
}
