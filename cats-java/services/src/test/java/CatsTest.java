import com.itmo.cats.dao.implementations.CatDao;
import com.itmo.cats.dao.implementations.OwnerDao;
import com.itmo.cats.models.Cat;
import com.itmo.cats.models.Color;
import com.itmo.cats.models.Owner;
import com.itmo.cats.services.CatsService;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatsTest {
    private final Session _session = mock(Session.class);

    private final OwnerDao _ownerDao = mock(OwnerDao.class);

    private final CatDao _catDao = mock(CatDao.class);

    private final CatsService _catsService = new CatsService();

    @BeforeEach
    public void SetUp(){
        _catsService.setOwnerDao(_ownerDao);
        _catsService.setCatDao(_catDao);
    }

    @Test
    public void getCat_UserWantsToGetACat_CatIsFound(){
        Owner owner = new Owner("Ivan", new Date(123));
        Cat cat = new Cat("James", new Date(1234), "American", Color.black, owner);
        when(_catDao.getById(1)).thenReturn(cat);
        assertEquals(_catsService.getCat(1).getName(), "James");
    }

    @Test
    public void getOwner_UserWantsToGetACatOwner_OwnerIsFound(){
        Owner owner = new Owner("Ivan", new Date(123));
        when(_ownerDao.getById(1)).thenReturn(owner);
        assertEquals(_catsService.getOwner(1).getName(), "Ivan");
    }

    @Test
    public void getOwner_UserWantsToGetACatOwnerThatIsNotInDB_OwnerIsNotFound(){
        when(_ownerDao.getById(2)).thenReturn(null);
        assertNull(_catsService.getOwner(2));
    }

    @Test
    public void getOwner_UserWantsToGetACatOwner_OwnerIsFoundInMockedHibernate(){
        OwnerDao ownerDao = new OwnerDao();
        ownerDao.setSession(_session);
        _catsService.setOwnerDao(ownerDao);
        Owner owner = new Owner("Ivan", new Date(123));
        when(_session.get(Owner.class, 1)).thenReturn(owner);
        assertEquals(_catsService.getOwner(1).getName(), "Ivan");
    }
}
