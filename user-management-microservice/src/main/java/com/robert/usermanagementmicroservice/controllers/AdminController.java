package com.robert.usermanagementmicroservice.controllers;

import com.robert.usermanagementmicroservice.dtos.ClientCreateDTO;
import com.robert.usermanagementmicroservice.dtos.ClientDTO;
import com.robert.usermanagementmicroservice.entities.Admin;
import com.robert.usermanagementmicroservice.services.AdminService;
import com.robert.usermanagementmicroservice.services.ClientService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("admin/clients")
@CrossOrigin
public class AdminController {
    private final ClientService clientService;
    private final AdminService adminService;

    @Autowired
    public AdminController(ClientService clientService, AdminService adminService) {
        this.clientService = clientService;
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<UUID> createClient(@Valid @RequestBody ClientCreateDTO clientCreateDTO) {
        UUID clientID = clientService.insertClient(clientCreateDTO);
        return new ResponseEntity<>(clientID, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable("id") UUID clientID) {
        return new ResponseEntity<>(clientService.getClientById(clientID), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable UUID id, @Valid @RequestBody ClientCreateDTO clientCreateDTO) {
        ClientDTO clientDTO = clientService.updateClient(id, clientCreateDTO);
        return new ResponseEntity<>(clientDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save-admin")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }
}
