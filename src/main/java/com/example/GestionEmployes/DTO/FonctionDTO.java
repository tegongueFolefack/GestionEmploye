package com.example.GestionEmployes.DTO;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.Models.Admin;
import com.example.GestionEmployes.Models.Fonction;

import lombok.Data;

@Data
public class FonctionDTO {

	private double heure_travail_semaine;
	private double salaire_heure;
	private String type_fonction;
	
	public Fonction toFonction() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Fonction.class);
    }
}
