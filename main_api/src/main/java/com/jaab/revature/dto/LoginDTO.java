package com.jaab.revature.dto;

import com.jaab.revature.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private Long id;
    private String email;
    private String password;
    private Role role;
}
