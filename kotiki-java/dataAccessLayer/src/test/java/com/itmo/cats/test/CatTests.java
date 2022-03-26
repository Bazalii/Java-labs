package com.itmo.cats.test;

import com.itmo.cats.cats.repositories.CatRepositoryImpl;
import com.itmo.cats.domains.Color;
import com.itmo.cats.domains.cats.Cat;
import com.itmo.cats.domains.cats.repositories.CatRepository;
import com.itmo.cats.domains.cats.services.CatService;
import com.itmo.cats.domains.cats.services.implementations.CatServiceImpl;
import com.itmo.cats.domains.owners.Owner;
import com.itmo.cats.domains.owners.repositories.OwnerRepository;
import com.itmo.cats.domains.owners.services.OwnerService;
import com.itmo.cats.domains.owners.services.implementations.OwnerServiceImpl;
import com.itmo.cats.owners.repositories.OwnerRepositoryImpl;
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