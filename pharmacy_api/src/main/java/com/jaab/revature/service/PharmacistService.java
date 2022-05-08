package com.jaab.revature.service;

import com.jaab.revature.dao.PharmacistRepository;
import com.jaab.revature.dto.UserDTO;
import com.jaab.revature.model.Pharmacist;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.rmi.ServerException;

@Service
public class PharmacistService {

    private PharmacistRepository pharmacistRepository;
    private final WebClient webClient;

    @Autowired
    public PharmacistService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://mainapi:8081").build();
    }

    @Autowired
    public void setPharmacistRepository(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    /**
     * Saves a new pharmacist to the pharmacist database
     * @param pharmacist - the new pharmacist
     * @return - the userDTO payload
     */
    public UserDTO createPharmacist(Pharmacist pharmacist) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(pharmacist, userDTO);
        UserDTO userData = sendUserData(userDTO);
        pharmacist.setId(userData.getId());
        pharmacistRepository.save(pharmacist);
        return userData;
    }

    /**
     * Sends a userDTO object to the Main API
     * @param userDTO - the userDTO object
     * @return - the userDTO payload
     */
    private UserDTO sendUserData(UserDTO userDTO) {
        return webClient.post()
                .uri("/newPharmacist")
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
