package com.itmo.cats.user;

import com.itmo.cats.coremodels.user.User;
import com.itmo.cats.coremodels.user.UserCreationModel;
import com.itmo.cats.dtomodels.user.UserCreationRequest;
import com.itmo.cats.dtomodels.user.UserResponse;
import com.itmo.cats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService _userService;

    @Autowired
    public UserController(UserService userService) {
        _userService = userService;
    }

    @PostMapping(value = "create")
    public UserResponse Create(@RequestBody UserCreationRequest request) {
        var user = _userService.add(castUserCreationRequestToUserCreationModel(request));
        return castUserToUserResponse(user);
    }

    @PostMapping(value = "delete")
    public void Delete(@RequestParam(value = "id") int id) {
        _userService.deleteById(id);
    }

    private UserCreationModel castUserCreationRequestToUserCreationModel(UserCreationRequest request) {
        return new UserCreationModel(request.getId(), request.getUsername(), encodePassword(request.getPassword()));
    }

    private String encodePassword(String password) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private UserResponse castUserToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getPassword());
    }
}
