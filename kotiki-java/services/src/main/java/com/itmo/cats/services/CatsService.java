package com.itmo.cats.services;

import com.itmo.cats.dao.CatDao;
import com.itmo.cats.dao.OwnerDao;
import com.itmo.cats.dao.implementations.CatDaoImpl;
import com.itmo.cats.dao.implementations.OwnerDaoImpl;
import com.itmo.cats.models.Cat;
import com.itmo.cats.models.Owner;

import java.util.List;

public class CatsService {
    private OwnerDao _ownerDao = new OwnerDaoImpl();

    private CatDao _catDao = new CatDaoImpl();

    public void setCatDao(CatDaoImpl catDao) {
        _catDao = catDao;
    }

    public void setOwnerDao(OwnerDaoImpl ownerDao) {
        _ownerDao = ownerDao;
    }

    public Owner getOwner(int id) {
        return _ownerDao.getById(id);
    }

    public void saveOwner(Owner owner) {
        _ownerDao.save(owner);
    }

    public void deleteOwner(Owner owner) {
        _ownerDao.delete(owner);
    }

    public void updateOwner(Owner owner) {
        _ownerDao.update(owner);
    }

    public List<Owner> getAllOwners() {
        return _ownerDao.getAll();
    }

    public Cat getCat(int id) {
        return _catDao.getById(id);
    }

    public void saveCat(Cat cat) {
        _catDao.save(cat);
    }

    public void deleteCat(Cat cat) {
        _catDao.delete(cat);
    }

    public void updateCat(Cat cat) {
        _catDao.update(cat);
    }

    public List<Cat> getAllCats() {
        return _catDao.getAll();
    }

    public void AddFriendById(int firstCatId, int secondCatId) {
        var dbModel = _catDao.getById(firstCatId);
        dbModel.addFriend(_catDao.getById(secondCatId));
        _catDao.update(dbModel);
    }
}
