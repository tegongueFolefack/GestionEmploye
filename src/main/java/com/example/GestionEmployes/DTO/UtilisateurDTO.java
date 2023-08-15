package com.example.GestionEmployes.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class UtilisateurDTO {
	
	private Long UserId;
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
	private String Nom;
	private String Prenom;
}
