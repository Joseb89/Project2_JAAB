package com.jaab.revature.service;

import com.jaab.revature.dao.DoctorRepository;
import com.jaab.revature.dto.UserDTO;
import com.jaab.revature.model.Doctor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.rmi.ServerException;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private final WebClient webClient;

    @Autowired
    public DoctorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://project2main-service:8081").build();
    }

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     * Saves a new doctor to the doctor database
     * @param doctor - the new doctor
     * @return - the userDTO payload
     */
    public UserDTO createDoctor(Doctor doctor) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(doctor, userDTO);
        UserDTO userData = sendUserData(userDTO);
        doctor.setId(userData.getId());
        doctorRepository.save(doctor);
        return userData;
    }

    /**
     * Sends UserDTO object to the Main API
     * @param userDTO - the userDTO object
     * @return - the userDTO payload
     */
    private UserDTO sendUserData(UserDTO userDTO) {
        return webClient.post()
                .uri("/newDoctor")
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
