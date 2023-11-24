package com.robert.usermanagementmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;
@Setter
@Getter
public class ClientDTO {
    @NotNull
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String username;
    // Error ?
    @NotNull
    private String password;

    public ClientDTO() {
    }

    public ClientDTO(UUID id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
