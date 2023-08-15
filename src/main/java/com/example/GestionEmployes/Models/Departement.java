package com.example.GestionEmployes.Models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Departement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int ID_depart;
	
	private String Nom_depart;

	@ManyToOne
	private Admin admin;
	
	@ManyToMany(mappedBy = "departement")
	private Collection<Fonction> fonction =new ArrayList<>();
	
	
	
	@OneToMany
	private Collection<Utilisateur> utilisateur = new ArrayList<>();
	
	public Departement(String nom_depart) {
		super();
		Nom_depart = nom_depart;
	}


	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
