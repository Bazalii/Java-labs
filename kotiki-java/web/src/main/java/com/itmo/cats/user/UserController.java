package com.itmo.cats.user;

import com.itmo.cats.domain.user.UserCreationModel;
import com.itmo.cats.domain.user.service.UserService;
import com.itmo.cats.owner.dto.OwnerCreationRequest;
import com.itmo.cats.user.dto.UserCreationRequest;
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
    public void Create(@RequestBody UserCreationRequest request) {
        _userService.add(castUserCreationRequestToUserCreationModel(request));
    }

    @PostMapping(value = "delete")
    public void Delete(@RequestParam(value = "id") int id) {
        _userService.deleteById(id);
    }

    private UserCreationModel castUserCreationRequestToUserCreationModel(UserCreationRequest request) {
        return new UserCreationModel(request.getId(), request.getUsername(), encodePassword(request.getPassword()));
    }

    private String encodePassword(String password){
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
