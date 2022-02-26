import com.itmo.cats.dao.implementations.CatDao;
import com.itmo.cats.models.Cat;
import com.itmo.cats.models.Color;
import com.itmo.cats.models.Owner;
import com.itmo.cats.services.CatsService;
import com.itmo.cats.utils.HibernateSessionFactory;

import java.util.Date;

public class Program {
    public static void main(String[] args){
        CatDao catDao = new CatDao();
        catDao.setSession(HibernateSessionFactory.getSessionFactory().openSession());
        CatsService service = new CatsService();
        service.setCatDao(catDao);
        Owner owner = new Owner("Ivan", new Date(123));
        service.saveOwner(owner);
        Cat cat = new Cat("James", new Date(1234), "American", Color.black, owner);
        service.saveCat(cat);
        owner.AddCat(cat);
        service.updateOwner(owner);
    }
}
