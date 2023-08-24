package com.example.GestionEmployes;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class GestionEmployesApplication  implements CommandLineRunner {
	
	@Autowired
    private com.example.GestionEmployes.Services.BDInitService BDInitService;
	
	@Bean
	public ModelMapper modelMapper () {
		return new ModelMapper();
	} 

	public static void main(String[] args) {
		SpringApplication.run(GestionEmployesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@PostConstruct
    public void init() {
		BDInitService.initializeUsers();
    }

} 
