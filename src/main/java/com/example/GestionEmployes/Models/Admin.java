package com.example.GestionEmployes.Models;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.mapping.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;







@Entity
@Table(name = "admin")
@DiscriminatorValue("admin")
public class Admin extends Utilisateur {

	@OneToMany (mappedBy="admin")
	private Collection<Paiement> paiement =new ArrayList<>();
	
	@OneToMany(mappedBy = "admin")
	private Collection<Departement>departement = new ArrayList<>();
	
	@OneToMany(mappedBy = "admin")
	private Collection<Planning>planning = new ArrayList<>();
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String nom, String prenom, String passWord, String login, Date date_creation, String genre,
			String etat_Civil, int telephone, int matricule, String email, String compteIBAN, String addresse) {
		super(nom, prenom, passWord, login, date_creation, genre, etat_Civil, telephone, matricule, email, compteIBAN,
				addresse);
		// TODO Auto-generated constructor stub
	}
	
	

}
