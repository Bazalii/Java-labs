package com.itmo.cats.Dao;

import com.itmo.cats.Models.Cat;
import com.itmo.cats.Models.Owner;
import com.itmo.cats.Utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class OwnerDao {
    public Owner getById(Integer id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Owner.class, id);
    }

    public void save(Owner owner){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();
        session.close();
    }

    public void update(Owner owner){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(owner);
        transaction.commit();
        session.close();
    }

    public void delete(Owner owner){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(owner);
        transaction.commit();
        session.close();
    }

    public ArrayList<Owner> getAll(){
        return (ArrayList<Owner>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Owner").list();
    }
}
