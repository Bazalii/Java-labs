package com.itmo.cats.user.repository;

import com.itmo.cats.domain.user.User;
import com.itmo.cats.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserDao _userDao;

    @Autowired
    public UserRepositoryImpl(UserDao userDao) {
        _userDao = userDao;
    }

    @Override
    public void add(User model) {
        _userDao.save(new UserDbModel(model.getId(), model.getUsername(),
                model.getPassword(), model.getRole(), model.getIsEnabled()));
    }

    @Override
    public void deleteById(int id) {
        _userDao.deleteById(id);
    }
}
