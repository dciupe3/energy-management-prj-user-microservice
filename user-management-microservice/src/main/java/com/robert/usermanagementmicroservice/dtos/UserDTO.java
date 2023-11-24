package com.robert.usermanagementmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserDTO {
    private String username;
    private String password;
}
