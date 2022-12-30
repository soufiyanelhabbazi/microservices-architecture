package me.habbazi.customerservice;

import me.habbazi.customerservice.dto.CustomerRequestDTO;
import me.habbazi.customerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerService customerService) {
		return args -> {
			customerService.saveCustomer(new CustomerRequestDTO(UUID.randomUUID().toString(), "Soufiyan", "soufiyan@habbazi.me"));
			customerService.saveCustomer(new CustomerRequestDTO(UUID.randomUUID().toString(), "Amine", "amine@habbazi.me"));
		};
	}
}
