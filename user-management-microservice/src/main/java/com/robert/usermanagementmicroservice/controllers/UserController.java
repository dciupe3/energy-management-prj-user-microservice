package com.robert.usermanagementmicroservice.controllers;

import com.robert.usermanagementmicroservice.dtos.UserDTO;
import com.robert.usermanagementmicroservice.dtos.UserWithoutPassDTO;
import com.robert.usermanagementmicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserWithoutPassDTO> login(@RequestBody UserDTO userDTO) {
        try {
            UserWithoutPassDTO userWithoutPassDTO = userService.login(userDTO);
            return ResponseEntity.ok(userWithoutPassDTO);
        }  catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
