package com.robert.usermanagementmicroservice.services;

import com.robert.usermanagementmicroservice.entities.Admin;
import com.robert.usermanagementmicroservice.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin saveAdmin(Admin admin) {
        if(adminRepository.findByUsername("admin") == null) {
            return adminRepository.save(admin);
        }
        return null;
    }
}
