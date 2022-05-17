package com.itmo.cats.dbModels.owner;

import com.itmo.cats.dbModels.cat.CatDbModel;
import com.itmo.cats.coreModels.cat.Cat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "owners")
public class OwnerDbModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int _id;

    @Column(name = "name")
    private String _name;

    @Column(name = "date_of_birth")
    private Date _birthDate;

    @OneToMany(mappedBy = "_owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CatDbModel> _cats;

    public OwnerDbModel() {
    }

    public OwnerDbModel(String name, Date dateOfBirth) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        _name = name;

        if (dateOfBirth == null)
            throw new IllegalArgumentException("Date cannot be null!");
        _birthDate = dateOfBirth;
        _cats = new ArrayList<>();
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Date getBirthDate() {
        return _birthDate;
    }

    public void setBirthDate(Date dateOfBirth) {
        _birthDate = dateOfBirth;
    }

    public void removeCat(int id) {
        _cats.removeIf(catDbModel -> catDbModel.getId() == id);
    }

    public List<Cat> getCats() {
        var result = new ArrayList<Cat>();
        for (CatDbModel dbModel : _cats) {
            var cat = new Cat();
            cat.setName(dbModel.getName());
            cat.setBirthDate(dbModel.getBirthDate());
            cat.setBreed(dbModel.getBreed());
            cat.setColor(dbModel.getColor());
            cat.setOwnerId(dbModel.getOwnerId());
            result.add(cat);
        }

        return result;
    }
}
