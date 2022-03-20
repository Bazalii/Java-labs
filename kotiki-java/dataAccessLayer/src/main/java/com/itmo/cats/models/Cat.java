package com.itmo.cats.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer _id;

    @Column(name = "name")
    private String _name;

    @Column(name = "date_of_birth")
    private Date _dateOfBirth;

    @Column(name = "breed")
    private String _breed;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color _color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner _owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cats_friends",
            joinColumns = {@JoinColumn(name = "cat_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    private List<Cat> _friends;

    public Cat() {
    }

    public Cat(String name, Date dateOfBirth, String breed, Color color, Owner owner) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null!");
        _name = name;

        if (dateOfBirth == null)
            throw new IllegalArgumentException("Date cannot be null!");
        _dateOfBirth = dateOfBirth;

        if (breed == null)
            throw new IllegalArgumentException("Breed cannot be null!");
        _breed = breed;

        _color = color;

        if (owner == null)
            throw new IllegalArgumentException("Id should be a positive integer!");
        _owner = owner;
        _friends = new ArrayList<>();
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }

    public String getBreed() {
        return _breed;
    }

    public void setBreed(String _breed) {
        this._breed = _breed;
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color _color) {
        this._color = _color;
    }

    public Owner getOwner() {
        return _owner;
    }

    public void setOwner(Owner _owner) {
        this._owner = _owner;
    }

    public void addFriend(Cat cat) {
        _friends.add(cat);

    }

    public void removeFriend(Cat cat) {
        _friends.remove(cat);
    }

    public List<Cat> GetFriends() {
        return _friends;
    }
}
