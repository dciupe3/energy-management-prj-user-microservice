package com.robert.usermanagementmicroservice.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DiscriminatorValue("ADMIN")
@Entity
public class Admin extends User {
    @Override
    public String getUserRole() {
        return "ADMIN";
    }
}
