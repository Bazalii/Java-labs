package com.itmo.cats.dao.implementations;

import com.itmo.cats.dao.IOwnerDao;
import com.itmo.cats.models.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OwnerDao implements IOwnerDao {
    private Session _session;

    public void setSession(Session session) {
        _session = session;
    }

    public Owner getById(Integer id) {
        return _session.get(Owner.class, id);
    }

    public void save(Owner owner) {
        Transaction transaction = _session.beginTransaction();
        _session.save(owner);
        transaction.commit();
        _session.close();
    }

    public void update(Owner owner) {
        Transaction transaction = _session.beginTransaction();
        _session.update(owner);
        transaction.commit();
        _session.close();
    }

    public void delete(Owner owner) {
        Transaction transaction = _session.beginTransaction();
        _session.delete(owner);
        transaction.commit();
        _session.close();
    }

    public List<Owner> getAll() {
        return _session.createQuery("From Owner").list();
    }
}
