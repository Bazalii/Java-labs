package com.itmo.cats.cats.Dto;

import com.itmo.cats.domains.Color;

import java.util.Date;

public class CatUpdateRequest {
    public int id;

    public String name;

    public Date birthDate;

    public String breed;

    public Color color;

    public Integer ownerId;
}
