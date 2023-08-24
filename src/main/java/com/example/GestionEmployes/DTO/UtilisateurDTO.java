package com.example.GestionEmployes.DTO;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.Models.Employe;
import com.example.GestionEmployes.Models.Utilisateur;

import lombok.Data;

@Data
public class UtilisateurDTO {
	
	
	private String passWord;
	private String login;
	private Date date_creation;
	private String genre;
	private String etat_Civil;
	private int telephone;
	private int matricule;
	private String email;
	private String compteIBAN;
	private String addresse;
	private String nom;
	private String prenom;
	private double salaireBase;
	private double tauxHoraire;
	private int heuresTravailFixes;
	private boolean transportPrive;
	
	public Utilisateur toUtilisateur() {
	    ModelMapper modelMapper = new ModelMapper();
	    return modelMapper.map(this, Utilisateur.class);
	}
}

