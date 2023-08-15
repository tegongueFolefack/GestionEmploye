package com.example.GestionEmployes.Models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class TypePlanning {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int ID_type_planning;
	
	private String nom_type;
	
	@OneToMany
	private Collection<Planning> plannings = new ArrayList<>();

	public TypePlanning(String nom_type) {
		super();
		this.nom_type = nom_type;
	}

	public TypePlanning() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
