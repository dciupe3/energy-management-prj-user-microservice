package com.robert.usermanagementmicroservice.controllers;

import com.robert.usermanagementmicroservice.dtos.DeviceDTO;
import com.robert.usermanagementmicroservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@CrossOrigin
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}/devices")
    public ResponseEntity<List<DeviceDTO>> getClientDevices(@PathVariable UUID clientId) {
        List<DeviceDTO> devices = clientService.getClientDevices(clientId);
        return ResponseEntity.ok(devices);
    }
}
