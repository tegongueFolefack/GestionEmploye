package com.example.GestionEmployes.Models;


import java.util.ArrayList;
import java.util.Collection;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Fonction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;
	
	private double heure_travail_semaine;
	private double Salaire_heure;
	private String type_fonction;
	
	@OneToMany
	private Collection<Planning> plannings = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Departement> departement = new ArrayList<>();
	
	@ManyToMany(fetch= FetchType.EAGER)
	private Collection<Avantages> avantages = new ArrayList<>();
	
	public Fonction(double heure_travail_semaine, double salaire_heure, String type_fonction) {
		super();
		this.heure_travail_semaine = heure_travail_semaine;
		Salaire_heure = salaire_heure;
		this.type_fonction = type_fonction;
	}


	public Fonction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
