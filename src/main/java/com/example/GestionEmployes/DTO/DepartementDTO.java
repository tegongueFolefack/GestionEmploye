package com.example.GestionEmployes.DTO;



import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.Models.Admin;
import com.example.GestionEmployes.Models.Departement;


import lombok.Data;

@Data
public class DepartementDTO {
	
	private String nom_departement;
	
	
	
	public Departement toDepartement() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Departement.class);
    }


}
