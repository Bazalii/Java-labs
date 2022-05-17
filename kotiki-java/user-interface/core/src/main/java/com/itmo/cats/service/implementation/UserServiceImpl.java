package com.itmo.cats.service.implementation;

import com.itmo.cats.coreModels.Role;
import com.itmo.cats.coreModels.user.User;
import com.itmo.cats.coreModels.user.UserCreationModel;
import com.itmo.cats.repository.UserRepository;
import com.itmo.cats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final Role _defaultRole = Role.ROLE_USER;

    private final UserRepository _userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public User add(UserCreationModel model) {
        return _userRepository.add(new User(model.getId(), model.getUsername(), model.getPassword(), _defaultRole, true));
    }

    @Override
    public void deleteById(int id) {
        _userRepository.deleteById(id);
    }
}
