package com.itmo.cats.Dao;

import com.itmo.cats.Models.Owner;
import java.util.ArrayList;

public interface IOwnerDao {
    Owner getById(Integer id);

    void save(Owner owner);

    void update(Owner owner);

    void delete(Owner owner);

    ArrayList<Owner> getAll();
}
