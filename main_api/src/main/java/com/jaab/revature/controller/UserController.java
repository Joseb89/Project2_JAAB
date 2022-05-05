package com.jaab.revature.controller;

import com.jaab.revature.dto.UserDTO;
import com.jaab.revature.model.Doctor;
import com.jaab.revature.model.Pharmacist;
import com.jaab.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/newPatient", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createPatient(@RequestBody UserDTO userDTO) {
        return userService.createPatient(userDTO);
    }

    @PostMapping(value = "/newDoctor", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public void createDoctor(@RequestBody Doctor doctor) {
        userService.createDoctor(doctor);
    }

    @PostMapping(value = "/newPharmacist", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public void createPharmacist(@RequestBody Pharmacist pharmacist) {
        userService.createPharmacist(pharmacist);
    }
}
