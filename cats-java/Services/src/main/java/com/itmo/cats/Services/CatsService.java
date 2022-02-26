package com.itmo.cats.Services;

import com.itmo.cats.Dao.Implementations.CatDao;
import com.itmo.cats.Dao.Implementations.OwnerDao;
import com.itmo.cats.Models.Cat;
import com.itmo.cats.Models.Owner;

import java.util.ArrayList;

public class CatsService {
    private OwnerDao _ownerDao = new OwnerDao();

    private CatDao _catDao = new CatDao();

    public void setCatDao(CatDao catDao){
        _catDao = catDao;
    }

    public void setOwnerDao(OwnerDao ownerDao){
        _ownerDao = ownerDao;
    }

    public Owner getOwner(int id){
        return _ownerDao.getById(id);
    }

    public void saveOwner(Owner owner){
        _ownerDao.save(owner);
    }

    public void deleteOwner(Owner owner){
        _ownerDao.delete(owner);
    }

    public void updateOwner(Owner owner){
        _ownerDao.update(owner);
    }

    public ArrayList<Owner> getAllOwners(){
        return _ownerDao.getAll();
    }

    public Cat getCat(int id){
        return _catDao.getById(id);
    }

    public void saveCat(Cat cat){
        _catDao.save(cat);
    }

    public void deleteCat(Cat cat){
        _catDao.delete(cat);
    }

    public void updateCat(Cat cat){
        _catDao.update(cat);
    }

    public ArrayList<Cat> getAllCats(){
        return _catDao.getAll();
    }
}
