package com.itmo.cats.cats.repositories;

import com.itmo.cats.domains.Color;
import com.itmo.cats.domains.owners.Owner;
import com.itmo.cats.owners.repositories.OwnerDbModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cats")
public class CatDbModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int _id;

    @Column(name = "name")
    private String _name;

    @Column(name = "date_of_birth")
    private Date _birthDate;

    @Column(name = "breed")
    private String _breed;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color _color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private OwnerDbModel _owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cats_friends",
            joinColumns = {@JoinColumn(name = "cat_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    private List<CatDbModel> _friends;

    public CatDbModel() {
    }

    public CatDbModel(String name, Date dateOfBirth, String breed, Color color, OwnerDbModel dbModel) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null!");
        _name = name;

        if (dateOfBirth == null)
            throw new IllegalArgumentException("Date cannot be null!");
        _birthDate = dateOfBirth;

        if (breed == null)
            throw new IllegalArgumentException("Breed cannot be null!");
        _breed = breed;

        _color = color;

        if (dbModel == null)
            throw new IllegalArgumentException("Id should be a positive integer!");
        _owner = dbModel;

        _friends = new ArrayList<>();
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public void setBirthDate(Date date) {
        _birthDate = date;
    }

    public Date getBirthDate() {
        return _birthDate;
    }

    public String getBreed() {
        return _breed;
    }

    public void setBreed(String breed) {
        _breed = breed;
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        _color = color;
    }

    public int getOwnerId() {
        return _owner.getId();
    }

    public void setOwner(Owner owner) {
        var dbModel = new OwnerDbModel();
        dbModel.setId(owner.getId());
        dbModel.setName(owner.getName());
        dbModel.setBirthDate(owner.getBirthDate());
        _owner = dbModel;
    }

    public void addFriend(CatDbModel dbModel) {
        _friends.add(dbModel);
    }

    public void removeFriend(int id) {
        _friends.removeIf(catDbModel -> Objects.equals(catDbModel.getId(), id));
    }

    public List<CatDbModel> getFriends() {
        return _friends;
    }
}