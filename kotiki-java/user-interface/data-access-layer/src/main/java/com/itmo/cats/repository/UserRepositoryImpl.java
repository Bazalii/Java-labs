package com.itmo.cats.repository;

import com.itmo.cats.coremodels.user.User;
import com.itmo.cats.dbmodels.user.UserDbModel;
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
    public User add(User model) {
        var dbModel = new UserDbModel(model.getId(), model.getUsername(),
                model.getPassword(), model.getRole(), model.getIsEnabled());

        var result = _userDao.save(dbModel);

        return castUserDbModelToUser(result);
    }

    @Override
    public void deleteById(int id) {
        _userDao.deleteById(id);
    }

    private User castUserDbModelToUser(UserDbModel userDbModel) {
        return new User(userDbModel.getId(), userDbModel.getUsername(), userDbModel.getPassword(),
                userDbModel.getRole(), userDbModel.getEnabled());
    }
}
