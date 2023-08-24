package com.example.GestionEmployes.DTO;

import org.modelmapper.ModelMapper;


import com.example.GestionEmployes.Models.Employe;

public class EmployeDTO extends UtilisateurDTO {
	
	
	public Employe toEmploye() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Employe.class);
    }

}
