package com.jaab.revature.controller;

import com.jaab.revature.model.Doctor;
import com.jaab.revature.model.Patient;
import com.jaab.revature.model.Pharmacist;
import com.jaab.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/newPatient", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public void createPatient(@RequestBody Patient patient) {
        userService.createPatient(patient);
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
