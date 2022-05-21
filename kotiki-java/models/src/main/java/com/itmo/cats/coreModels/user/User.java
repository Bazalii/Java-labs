package com.itmo.cats.coreModels.user;

import com.itmo.cats.coreModels.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class User {
    private int id;

    private String username;

    private String password;

    private Role role;

    private Boolean isEnabled;
}
