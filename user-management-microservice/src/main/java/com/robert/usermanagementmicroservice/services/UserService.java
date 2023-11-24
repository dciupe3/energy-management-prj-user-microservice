package com.robert.usermanagementmicroservice.services;

import com.robert.usermanagementmicroservice.dtos.UserDTO;
import com.robert.usermanagementmicroservice.dtos.UserWithoutPassDTO;
import com.robert.usermanagementmicroservice.entities.User;
import com.robert.usermanagementmicroservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserWithoutPassDTO login(UserDTO userDTO){
        User user = userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        if(user != null) {
            UserWithoutPassDTO userWithoutPassDTO = new UserWithoutPassDTO();
            userWithoutPassDTO.setUserRole(user.getUserRole());
            userWithoutPassDTO.setId(user.getId());
            return userWithoutPassDTO;
        }
        else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
