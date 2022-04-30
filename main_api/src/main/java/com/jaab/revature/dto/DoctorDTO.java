package com.jaab.revature.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String practice;
}
