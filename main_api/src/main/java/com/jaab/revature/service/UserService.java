package com.jaab.revature.service;

import com.jaab.revature.dao.PatientRepository;
import com.jaab.revature.dao.UserRepository;
import com.jaab.revature.model.Patient;
import com.jaab.revature.model.Role;
import com.jaab.revature.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PatientRepository patientRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Saves a new patient to the patient and users database
     * @param patient - the new patient
     */
    public void createPatient(Patient patient) {
        User user = new User();
        BeanUtils.copyProperties(patient, user);
        user.setRole(Role.PATIENT);
        userRepository.save(user);
        patient.setId(user.getId());
        patientRepository.save(patient);
    }

    /**
     * Saves a new user to the database
     * @param source - The new user created in previous method
     * @param role - The role of the user
     */
    private void createUser(Object source, Role role) {
        User user = new User();
        BeanUtils.copyProperties(source, user);
        user.setRole(role);
        userRepository.save(user);
    }
}
