import com.itmo.cats.Dao.Implementations.CatDao;
import com.itmo.cats.Models.Cat;
import com.itmo.cats.Models.Color;
import com.itmo.cats.Models.Owner;
import com.itmo.cats.Services.CatsService;
import com.itmo.cats.Utils.HibernateSessionFactory;

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
