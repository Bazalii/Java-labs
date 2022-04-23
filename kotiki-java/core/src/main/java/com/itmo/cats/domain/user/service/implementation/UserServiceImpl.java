package com.itmo.cats.domain.user.service.implementation;

import com.itmo.cats.domain.Role;
import com.itmo.cats.domain.user.User;
import com.itmo.cats.domain.user.UserCreationModel;
import com.itmo.cats.domain.user.repository.UserRepository;
import com.itmo.cats.domain.user.service.UserService;
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
    public void add(UserCreationModel model) {
        _userRepository.add(new User(model.getId(), model.getUsername(), model.getPassword(), _defaultRole, true));
    }

    @Override
    public void deleteById(int id) {
        _userRepository.deleteById(id);
    }
}
