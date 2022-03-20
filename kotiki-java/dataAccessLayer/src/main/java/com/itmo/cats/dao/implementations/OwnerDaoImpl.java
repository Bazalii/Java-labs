package com.itmo.cats.dao.implementations;

import com.itmo.cats.dao.OwnerDao;
import com.itmo.cats.models.Owner;
import com.itmo.cats.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    private Session _session;

    public void setSession(Session session) {
        _session = session;
    }

    public Owner getById(Integer id) {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        return _session.get(Owner.class, id);
    }

    public void save(Owner owner) {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = _session.beginTransaction();
        _session.save(owner);
        transaction.commit();
        _session.close();
    }

    public void update(Owner owner) {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = _session.beginTransaction();
        _session.update(owner);
        transaction.commit();
        _session.close();
    }

    public void delete(Owner owner) {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = _session.beginTransaction();
        _session.delete(owner);
        transaction.commit();
        _session.close();
    }

    public List<Owner> getAll() {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        return _session.createQuery("From Owner").list();
    }
}
