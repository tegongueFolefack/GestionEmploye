package com.example.GestionEmployes.DTO;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.Models.Role;
import com.example.GestionEmployes.Models.TypePlanning;

import lombok.Data;

@Data
public class TypePlanningDTO {
	
	private String nom_type;
	
	public TypePlanning toTypePlanning() {
	    ModelMapper modelMapper = new ModelMapper();
	    return modelMapper.map(this, TypePlanning.class);
	}

}
