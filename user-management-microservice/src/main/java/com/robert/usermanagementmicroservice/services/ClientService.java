package com.robert.usermanagementmicroservice.services;

import com.robert.usermanagementmicroservice.controllers.handlers.exceptions.model.ResourceNotFoundException;
import com.robert.usermanagementmicroservice.dtos.ClientCreateDTO;
import com.robert.usermanagementmicroservice.dtos.ClientDTO;
import com.robert.usermanagementmicroservice.dtos.DeviceDTO;
import com.robert.usermanagementmicroservice.dtos.builders.ClientBuilder;
import com.robert.usermanagementmicroservice.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.robert.usermanagementmicroservice.entities.Client;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ClientService(ClientRepository clientRepository, RestTemplate restTemplate) {
        this.clientRepository = clientRepository;
        this.restTemplate = restTemplate;
    }

    public UUID insertClient(ClientCreateDTO clientCreateDTO) {
        Client client = ClientBuilder.toEntity(clientCreateDTO);
        client = clientRepository.save(client);
        LOGGER.debug("Client with id {} was inserted in db", client.getId());
        return client.getId();
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientBuilder::toClientDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(UUID clientId) {
        Optional<Client> prosumerOptional = clientRepository.findById(clientId);
        if(prosumerOptional.isEmpty()) {
            LOGGER.error("Client with id {} was not found in db", clientId);
            throw new ResourceNotFoundException(Client.class.getSimpleName() + " with id: " + clientId);
        }
        return ClientBuilder.toClientDTO(prosumerOptional.get());
    }

    public ClientDTO updateClient(UUID clientId, ClientCreateDTO clientCreateDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(Client.class.getSimpleName() + " with id: " + clientId));

        if(clientCreateDTO.getName() != null) client.setName(clientCreateDTO.getName());
        if(clientCreateDTO.getUsername() != null) client.setUsername(clientCreateDTO.getUsername());
        if(clientCreateDTO.getPassword() != null) client.setPassword(clientCreateDTO.getPassword());

        LOGGER.debug("Client with id {} was updated in db", client.getId());

        return ClientBuilder.toClientDTO(clientRepository.save(client));
    }

    public void deleteClient(UUID clientId)  {
        if(!clientRepository.existsById(clientId)) {
            LOGGER.error("Client with id {} was not found in db", clientId);
            throw new ResourceNotFoundException(Client.class.getSimpleName() + " with id: " + clientId);
        }
        clientRepository.deleteById(clientId);
    }

    public List<DeviceDTO> getClientDevices(UUID clientId) {
        String deviceServiceUrl = "hhttp://localhost:8081/device-management-microservice/device-mappings/devices/client/" + clientId;

        ResponseEntity<DeviceDTO[]> response = restTemplate.getForEntity(deviceServiceUrl, DeviceDTO[].class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            throw new ResourceNotFoundException("Devices for client with id " + clientId + " not found");
        }
    }

}
