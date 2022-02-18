package com.itmo.cats.Dao;

import com.itmo.cats.Models.Cat;
import com.itmo.cats.Utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CatDao {
    public Cat getById(Integer id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Cat.class, id);
    }

    public void save(Cat cat){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cat);
        transaction.commit();
        session.close();
    }

    public void update(Cat cat){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cat);
        transaction.commit();
        session.close();
    }

    public void delete(Cat cat){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(cat);
        transaction.commit();
        session.close();
    }

    public ArrayList<Cat> getAll(){
        return (ArrayList<Cat>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Cat").list();
    }
}
