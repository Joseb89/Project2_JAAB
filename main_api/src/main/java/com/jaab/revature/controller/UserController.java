package com.jaab.revature.controller;

import com.jaab.revature.dto.UserDTO;
import com.jaab.revature.model.Role;
import com.jaab.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/newPatient", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createPatient(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO, Role.PATIENT);
    }

    @PostMapping(value = "/newDoctor", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createDoctor(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO, Role.PHYSICIAN);
    }

    @PostMapping(value = "/newPharmacist", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createPharmacist(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO, Role.PHARMACIST);
    }
}
