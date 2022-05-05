package com.jaab.revature.controller;

import com.jaab.revature.dto.UserDTO;
import com.jaab.revature.model.Doctor;
import com.jaab.revature.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/newDoctor", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createDoctor(@RequestBody Doctor doctor) {
        UserDTO newDoctor = doctorService.createDoctor(doctor);
        return ResponseEntity.ok(newDoctor.getId());
    }
}
