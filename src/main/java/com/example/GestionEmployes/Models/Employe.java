package com.example.GestionEmployes.Models;



import java.util.Date;



import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "employe")
@DiscriminatorValue("employe")
public class Employe extends Utilisateur {
	
	
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employe(String nom, String prenom, String passWord, String login, Date date_creation, String genre,
			String etat_Civil, int telephone, int matricule, String email, String compteIBAN, String addresse) {
		super(nom, prenom, passWord, login, date_creation, genre, etat_Civil, telephone, matricule, email, compteIBAN,
				addresse);
		
	}
	
	

}
