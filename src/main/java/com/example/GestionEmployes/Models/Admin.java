package com.example.GestionEmployes.Models;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.mapping.List;
import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.DTO.AdminDTO;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

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

	/**
	 * @param userId
	 * @param passWord
	 * @param login
	 * @param date_creation
	 * @param genre
	 * @param etat_Civil
	 * @param telephone
	 * @param matricule
	 * @param email
	 * @param compteIBAN
	 * @param addresse
	 * @param nom
	 * @param prenom
	 */
	

	public AdminDTO toAdminDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AdminDTO.class);
    }

	public Admin(Long userId, String passWord, String login, Date date_creation, String genre, String etat_Civil,
			int telephone, int matricule, String email, String compteIBAN, String addresse, String nom, String prenom,
			double salaireBase, double tauxHoraire, int heuresTravailFixes, boolean transportPrive) {
		super(userId, passWord, login, date_creation, genre, etat_Civil, telephone, matricule, email, compteIBAN, addresse, nom,
				prenom, salaireBase, tauxHoraire, heuresTravailFixes, transportPrive);
		// TODO Auto-generated constructor stub
	}

	
	
	
	

}
