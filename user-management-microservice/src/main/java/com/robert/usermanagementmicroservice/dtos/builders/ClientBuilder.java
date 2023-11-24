package com.robert.usermanagementmicroservice.dtos.builders;


import com.robert.usermanagementmicroservice.dtos.ClientCreateDTO;
import com.robert.usermanagementmicroservice.dtos.ClientDTO;
import com.robert.usermanagementmicroservice.entities.Client;

public class ClientBuilder {
    private ClientBuilder(){

    }

    public static ClientDTO toClientDTO(Client client) {
        return new ClientDTO(client.getId(), client.getName(), client.getUsername(), client.getPassword());
    }

    public static Client toEntity(ClientCreateDTO clientCreateDTO) {
        return new Client(clientCreateDTO.getName(),
                clientCreateDTO.getUsername(),
                clientCreateDTO.getPassword());
    }

    public static Client toEntity(ClientDTO clientDTO) {
        return new Client(clientDTO.getName(),
                clientDTO.getUsername(),
                clientDTO.getPassword());
    }
}
