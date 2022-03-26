package com.itmo.cats.test;

import com.itmo.cats.cat.repository.CatRepositoryImpl;
import com.itmo.cats.domain.Color;
import com.itmo.cats.domain.cat.Cat;
import com.itmo.cats.domain.cat.repository.CatRepository;
import com.itmo.cats.domain.cat.service.CatService;
import com.itmo.cats.domain.cat.service.implementation.CatServiceImpl;
import com.itmo.cats.domain.owner.Owner;
import com.itmo.cats.domain.owner.repository.OwnerRepository;
import com.itmo.cats.domain.owner.service.OwnerService;
import com.itmo.cats.domain.owner.service.implementation.OwnerServiceImpl;
import com.itmo.cats.owner.repository.OwnerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatTests {
    private final OwnerRepository _ownerRepository = mock(OwnerRepositoryImpl.class);

    private final CatRepository _catRepository = mock(CatRepositoryImpl.class);

    private CatService _catService;

    private OwnerService _ownerService;

    @BeforeEach
    public void SetUp() {
        _catService = new CatServiceImpl(_catRepository);
        _ownerService = new OwnerServiceImpl(_ownerRepository);
    }

    @Test
    public void getCat_UserWantsToGetACat_CatIsFound() {
        Cat cat = new Cat();
        cat.setName("Lola");
        cat.setBirthDate(new Date(1234));
        cat.setBreed("American");
        cat.setColor(Color.YELLOW);
        cat.setOwnerId(23);
        when(_catRepository.getById(1)).thenReturn(cat);
        assertEquals(_catService.getById(1).getName(), "Lola");
        assertEquals(_catService.getById(1).getOwnerId(), 23);
    }

    @Test
    public void getOwner_UserWantsToGetACatOwner_OwnerIsFound() {
        Owner owner = new Owner();
        owner.setName("Ivan");
        owner.setBirthDate(new Date(1234));
        when(_ownerRepository.getById(1)).thenReturn(owner);
        assertEquals(_ownerService.getById(1).getName(), "Ivan");
    }

    @Test
    public void getOwner_UserWantsToGetACatOwnerThatIsNotInDB_OwnerIsNotFound() {
        when(_ownerRepository.getById(2)).thenReturn(null);
        assertNull(_ownerService.getById(2));
    }
}