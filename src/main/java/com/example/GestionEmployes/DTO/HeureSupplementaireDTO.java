package com.example.GestionEmployes.DTO;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.Models.HeureSupplementaire;
import com.example.GestionEmployes.Models.Paiement;

import lombok.Data;

@Data
public class HeureSupplementaireDTO {
	
	 private LocalDate date;
	    private int nombreHeures;
	    private boolean valide;

	    public HeureSupplementaire toHeure_Supplementaire() {
	        ModelMapper modelMapper = new ModelMapper();
	        return modelMapper.map(this, HeureSupplementaire.class);
	    }
}
