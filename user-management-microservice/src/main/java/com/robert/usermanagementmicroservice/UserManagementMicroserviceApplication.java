package com.robert.usermanagementmicroservice;

import com.robert.usermanagementmicroservice.controllers.AdminController;
import com.robert.usermanagementmicroservice.entities.Admin;
import com.robert.usermanagementmicroservice.services.AdminService;
import com.robert.usermanagementmicroservice.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserManagementMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementMicroserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AdminService adminService /*, ClientService clientService*/) {
		return (args) -> {
			Admin admin = new Admin();
			admin.setPassword("admin");
			admin.setName("Robert");
			admin.setUsername("admin");
//			AdminController adminController = new AdminController(clientService,adminService);
			adminService.saveAdmin(admin);
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
