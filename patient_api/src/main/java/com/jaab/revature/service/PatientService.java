package com.jaab.revature.service;

import com.jaab.revature.dao.PatientRepository;
import com.jaab.revature.dto.UserDTO;
import com.jaab.revature.model.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.rmi.ServerException;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private final WebClient webClient;

    @Autowired
    public PatientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://mainapi:8081").build();
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Saves a new patient to the patient database
     * @param patient - the new Patient
     * @return - the user payload
     */
    public UserDTO createPatient(Patient patient) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(patient, userDTO);
        UserDTO userData =  sendUserData(userDTO);
        patient.setId(userData.getId());
        patientRepository.save(patient);
        return userData;
    }

    /**
     * Sends a userDTO payload to the Main Api and returns the user id
     * @param userDTO - the userDTO object
     * @return - the userDTO post payload
     */
    private UserDTO sendUserData(UserDTO userDTO) {
        return webClient.post()
                .uri("/newPatient")
                .body(Mono.just(userDTO), UserDTO.class)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError,
                        response -> response.bodyToMono(String.class).map(ServerException::new))
                .onStatus(HttpStatus::is4xxClientError,
                        response -> response.bodyToMono(String.class).map(Exception::new))
                .bodyToMono(UserDTO.class)
                .block();
    }
}
