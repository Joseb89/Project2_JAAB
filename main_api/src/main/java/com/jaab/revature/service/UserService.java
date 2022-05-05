package com.jaab.revature.service;

import com.jaab.revature.dao.DoctorRepository;
import com.jaab.revature.dao.PharmacistRepository;
import com.jaab.revature.dao.UserRepository;
import com.jaab.revature.dto.UserDTO;
import com.jaab.revature.model.Doctor;
import com.jaab.revature.model.Pharmacist;
import com.jaab.revature.model.Role;
import com.jaab.revature.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private DoctorRepository doctorRepository;
    private PharmacistRepository pharmacistRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @Autowired
    public void setPharmacistRepository(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    /**
     * Saves a new patient to the patient and users database
     * @param userDTO - the new patient
     */
    public Long createPatient(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setRole(Role.PATIENT);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * Saves a new doctor to the doctor and users database
     * @param doctor - the new doctor
     */
    public void createDoctor(Doctor doctor) {
        User user = new User();
        BeanUtils.copyProperties(doctor, user);
        user.setRole(Role.PHYSICIAN);
        userRepository.save(user);
        doctor.setId(user.getId());
        doctorRepository.save(doctor);
    }

    /**
     * Saves a new pharmacist to the pharmacist and users database
     * @param pharmacist - the new pharmacist
     */
    public void createPharmacist(Pharmacist pharmacist) {
        User user = new User();
        BeanUtils.copyProperties(pharmacist, user);
        user.setRole(Role.PHARMACIST);
        userRepository.save(user);
        pharmacist.setId(user.getId());
        pharmacistRepository.save(pharmacist);
    }
}
