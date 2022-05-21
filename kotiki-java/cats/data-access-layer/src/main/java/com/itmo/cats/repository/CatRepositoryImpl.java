package com.itmo.cats.repository;

import com.itmo.cats.coremodels.Role;
import com.itmo.cats.coremodels.cat.Cat;
import com.itmo.cats.dbmodels.cat.CatDbModel;
import com.itmo.cats.dtomodels.cat.GetAllCatsByIdMessage;
import com.itmo.cats.dtomodels.cat.GetCatByIdMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CatRepositoryImpl implements CatRepository {
    private final CatDao _catDao;

    private final OwnerDao _ownersDto;

    private final UserDao _userDao;

    @Autowired
    public CatRepositoryImpl(CatDao catDao, OwnerDao ownersDto, UserDao userDao) {
        _catDao = catDao;
        _ownersDto = ownersDto;
        _userDao = userDao;
    }

    @Override
    public Cat getById(GetCatByIdMessage getCatByIdMessage) {
        var dbModel = _catDao.getById(getCatByIdMessage.getId());

        var catOwner = _userDao.getById(dbModel.getOwnerId());

        if (!Objects.equals(catOwner.getUsername(), getCatByIdMessage.getUsername()) &&
                !Objects.equals(catOwner.getRole(), Role.ROLE_ADMIN)) {
            return null;
        }

        return castDbModelToCat(dbModel);
    }

    @Override
    public Cat add(Cat cat) {
        var dbModel = new CatDbModel(cat.getName(),
                cat.getBirthDate(),
                cat.getBreed(),
                cat.getColor(),
                _ownersDto.getById(cat.getOwnerId()));
        var result = _catDao.save(dbModel);
        return castDbModelToCat(result);
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
    public List<Cat> getAll(GetAllCatsByIdMessage getAllCatsByIdMessage) {
                var catOwner = _userDao.findByUsername(getAllCatsByIdMessage.getUsername());

        var dbModels = Objects.equals(catOwner.getRole(), Role.ROLE_ADMIN) ?
                _catDao.findAll() : _catDao.findAllByOwnerId(catOwner.getId());

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
