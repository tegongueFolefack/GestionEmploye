package com.example.GestionEmployes.DTO;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.Models.Admin;


public class AdminDTO extends UtilisateurDTO{
	
	public Admin toAdmin() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Admin.class);
    }
	

}
