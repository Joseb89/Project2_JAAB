package com.jaab.revature.service;

import com.jaab.revature.dao.UserRepository;
import com.jaab.revature.dto.UserDTO;
import com.jaab.revature.model.Role;
import com.jaab.revature.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a new user to the users database
     * @param userDTO - the user payload from external API
     * @param role - the user's role
     * @return - the user ID
     */
    public Long createUser(UserDTO userDTO, Role role) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setRole(role);
        userRepository.save(user);
        return user.getId();
    }
}
