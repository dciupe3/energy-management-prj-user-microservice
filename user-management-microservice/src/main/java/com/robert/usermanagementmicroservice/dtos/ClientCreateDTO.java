package com.robert.usermanagementmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ClientCreateDTO {
    @NotNull
    private String name;
    @NotNull
    private String username;
    // Error ?
    @NotNull
    private String password;

    public ClientCreateDTO(){

    }

    public ClientCreateDTO(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
