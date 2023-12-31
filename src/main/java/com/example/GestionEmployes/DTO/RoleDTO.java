package com.example.GestionEmployes.DTO;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.Models.Role;
import com.example.GestionEmployes.Models.Utilisateur;

import lombok.Data;

@Data
public class RoleDTO {
	
	private String role_Name;

	public Role toRole() {
	    ModelMapper modelMapper = new ModelMapper();
	    return modelMapper.map(this, Role.class);
	}
}
