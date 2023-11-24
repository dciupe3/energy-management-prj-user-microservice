package com.robert.usermanagementmicroservice.repositories;

import com.robert.usermanagementmicroservice.entities.Client;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.logging.Logger;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
}
