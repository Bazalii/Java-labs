import com.itmo.cats.dao.implementations.CatDaoImpl;
import com.itmo.cats.dao.implementations.OwnerDaoImpl;
import com.itmo.cats.models.Cat;
import com.itmo.cats.models.Color;
import com.itmo.cats.models.Owner;
import com.itmo.cats.services.CatsService;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        CatsService service = new CatsService();
        service.setCatDao(new CatDaoImpl());
        service.setOwnerDao(new OwnerDaoImpl());
        Owner owner = new Owner("Ivan", new Date(123));
        service.saveOwner(owner);
        Cat cat = new Cat("James", new Date(1234), "American", Color.BLACK, owner);
        service.saveCat(cat);
        owner.AddCat(cat);
        service.updateOwner(owner);
        Cat cat1 = new Cat("Alex", new Date(1234), "British", Color.BLACK, owner);
        cat.addFriend(cat1);
        service.updateCat(cat);
    }
}
