package com.robert.usermanagementmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserWithoutPassDTO {
    private UUID id;
    private String userRole;

    public UserWithoutPassDTO() {
    }
}
