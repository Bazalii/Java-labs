package com.itmo.cats.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer _id;

    @Column(name = "name")
    private String _name;

    @Column(name = "date_of_birth")
    private Date _dateOfBirth;

    @OneToMany(mappedBy = "_owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> _cats;

    public Owner(){
    }

    public Owner(String name, Date dateOfBirth){
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        _name = name;

        if (dateOfBirth == null)
            throw new IllegalArgumentException("Date cannot be null!");
        _dateOfBirth = dateOfBirth;
        _cats = new ArrayList<>();
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }

    public Date getDateOfBirth() {
        return _dateOfBirth;
    }

    public void setDateOfBirth(Date _dateOfBirth) {
        this._dateOfBirth = _dateOfBirth;
    }

    public void AddCat(Cat cat){
        _cats.add(cat);
    }

    public void RemoveCat(Cat cat){
        _cats.remove(cat);
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
}
