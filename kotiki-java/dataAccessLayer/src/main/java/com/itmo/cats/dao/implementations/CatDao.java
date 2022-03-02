package com.itmo.cats.dao.implementations;

import com.itmo.cats.dao.ICatDao;
import com.itmo.cats.models.Cat;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatDao implements ICatDao {
    private Session _session;

    public void setSession(Session session) {
        _session = session;
    }

    public Cat getById(Integer id) {
        return _session.get(Cat.class, id);
    }

    public void save(Cat cat) {
        Transaction transaction = _session.beginTransaction();
        _session.save(cat);
        transaction.commit();
        _session.close();
    }

    public void update(Cat cat) {
        Transaction transaction = _session.beginTransaction();
        _session.update(cat);
        transaction.commit();
        _session.close();
    }

    public void delete(Cat cat) {
        Transaction transaction = _session.beginTransaction();
        _session.delete(cat);
        transaction.commit();
        _session.close();
    }

    public List<Cat> getAll() {
        return _session.createQuery("From Cat").list();
    }
}
