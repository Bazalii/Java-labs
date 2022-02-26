package com.itmo.cats.Dao.Implementations;

import com.itmo.cats.Dao.IOwnerDao;
import com.itmo.cats.Models.Owner;
import com.itmo.cats.Utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class OwnerDao implements IOwnerDao {
    private Session _session;

    public void setSession(Session session){
        _session = session;
    }

    public Owner getById(Integer id){
        return _session.get(Owner.class, id);
    }

    public void save(Owner owner){
        Transaction transaction = _session.beginTransaction();
        _session.save(owner);
        transaction.commit();
        _session.close();
    }

    public void update(Owner owner){
        Transaction transaction = _session.beginTransaction();
        _session.update(owner);
        transaction.commit();
        _session.close();
    }

    public void delete(Owner owner){
        Transaction transaction = _session.beginTransaction();
        _session.delete(owner);
        transaction.commit();
        _session.close();
    }

    public ArrayList<Owner> getAll(){
        return (ArrayList<Owner>) _session.createQuery("From Owner").list();
    }
}
