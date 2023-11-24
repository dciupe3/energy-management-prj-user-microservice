package com.robert.usermanagementmicroservice.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Setter
@Getter
@Entity
@Table(name = "users")
@Inheritance(strategy =  InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {

    }
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public abstract String getUserRole();
}
