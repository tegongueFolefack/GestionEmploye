package com.example.GestionEmployes.Models;


import java.util.ArrayList;
import java.util.Collection;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.DTO.AvantagesDTO;
import com.example.GestionEmployes.DTO.UtilisateurDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Avantages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type_avantage;
	
	@ManyToMany(mappedBy = "avantages")
	private Collection<Fonction>fonction = new ArrayList<>();
	
	public Avantages(String type_avantage) {
		super();
		this.type_avantage = type_avantage;
	}


	public Avantages() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AvantagesDTO toAvantagesDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AvantagesDTO.class);
    }
	
	
	
}
