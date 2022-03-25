package com.itmo.cats.cats.repositories;

import com.itmo.cats.domains.cats.Cat;
import com.itmo.cats.domains.cats.repositories.CatRepository;
import com.itmo.cats.owners.repositories.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CatsRepositoryWrapper implements CatRepository {
    private final CatsDao _catsDao;

    private final OwnerDto _ownersDto;

    @Autowired
    public CatsRepositoryWrapper(CatsDao catsDao, OwnerDto ownersDto) {
        _catsDao = catsDao;
        _ownersDto = ownersDto;
    }

    @Override
    public Cat getById(int id) {
        var dbModel = _catsDao.getById(id);
        return CastDbModelToCat(dbModel);
    }

    @Override
    public void add(Cat cat) {
        var dbModel = new CatDbModel(cat.name,
                cat.birthDate,
                cat.breed,
                cat.color,
                _ownersDto.getById(cat.ownerId));
        _catsDao.save(dbModel);
    }

    @Override
    public void update(Cat cat) {
        var dbModel = new CatDbModel(cat.name,
                cat.birthDate,
                cat.breed,
                cat.color,
                _ownersDto.getById(cat.ownerId));
        dbModel.setId(cat.id);
        _catsDao.save(dbModel);
    }

    @Override
    public void deleteById(int id) {
        _catsDao.deleteById(id);
    }

    @Override
    public List<Cat> getAll() {
        var dbModels = _catsDao.findAll();
        var result = new ArrayList<Cat>();
        for (CatDbModel dbModel : dbModels) {
            Cat cat = CastDbModelToCat(dbModel);
            result.add(cat);
        }
        return result;
    }

    @Override
    public void addFriendById(int firstCatId, int secondCatId) {
        var cat = _catsDao.getById(firstCatId);
        cat.addFriend(_catsDao.getById(secondCatId));
        _catsDao.save(cat);
    }

    @Override
    public void removeFriendById(int firstCatId, int secondCatId) {
        var cat = _catsDao.getById(firstCatId);
        cat.removeFriend(secondCatId);
        _catsDao.save(cat);
    }

    private Cat CastDbModelToCat(CatDbModel dbModel) {
        var cat = new Cat();
        cat.id = dbModel.getId();
        cat.name = dbModel.getName();
        cat.birthDate = dbModel.getBirthDate();
        cat.breed = dbModel.getBreed();
        cat.color = dbModel.getColor();
        cat.ownerId = dbModel.getOwnerId();
        return cat;
    }
}
