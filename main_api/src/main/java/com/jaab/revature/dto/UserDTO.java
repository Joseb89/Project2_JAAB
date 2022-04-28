package com.jaab.revature.dto;

import com.jaab.revature.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO{
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
