package com.robert.usermanagementmicroservice.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DiscriminatorValue("CLIENT")
@Entity
public class Client extends User {

    public Client() {
    }
    public Client(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public String getUserRole() {
        return "CLIENT";
    }
}