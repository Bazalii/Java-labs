package com.itmo.cats.dao.implementations;

import com.itmo.cats.dao.CatDao;
import com.itmo.cats.models.Cat;
import com.itmo.cats.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatDaoImpl implements CatDao {
    private Session _session;

    public void setSession(Session session) {
        _session = session;
    }

    public Cat getById(Integer id) {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        return _session.get(Cat.class, id);
    }

    public void save(Cat cat) {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = _session.beginTransaction();
        _session.save(cat);
        transaction.commit();
        _session.close();
    }

    public void update(Cat cat) {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = _session.beginTransaction();
        _session.update(cat);
        transaction.commit();
        _session.close();
    }

    public void delete(Cat cat) {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = _session.beginTransaction();
        _session.delete(cat);
        transaction.commit();
        _session.close();
    }

    public List<Cat> getAll() {
        _session = HibernateSessionFactory.getSessionFactory().openSession();
        return _session.createQuery("From Cat").list();
    }
}
