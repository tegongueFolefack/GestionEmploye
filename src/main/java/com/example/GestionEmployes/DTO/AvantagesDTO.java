package com.example.GestionEmployes.DTO;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.Models.Avantages;


import lombok.Data;

@Data
public class AvantagesDTO {
	
	private String type_avantage;
	
	public Avantages toAvantages() {
	    ModelMapper modelMapper = new ModelMapper();
	    return modelMapper.map(this, Avantages.class);
	}
}
