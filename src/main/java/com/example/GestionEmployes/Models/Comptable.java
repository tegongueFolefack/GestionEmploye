package com.example.GestionEmployes.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.GestionEmployes.DTO.ComptableDTO;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "comptable")
@DiscriminatorValue("comptable")
public class Comptable extends Utilisateur{
	
	 
	    

	@OneToMany
	private Collection<Paiement> paiement =new ArrayList<>();
	
	
	
	
	public Comptable() {
		super();
		
	}




	
	



	









	public Comptable(Long userId, String passWord, String login, Date date_creation, String genre, String etat_Civil,
			int telephone, int matricule, String email, String compteIBAN, String addresse, String nom, String prenom,
			double salaireBase, double tauxHoraire, int heuresTravailFixes, boolean transportPrive) {
		super(userId, passWord, login, date_creation, genre, etat_Civil, telephone, matricule, email, compteIBAN, addresse, nom,
				prenom, salaireBase, tauxHoraire, heuresTravailFixes, transportPrive);
		// TODO Auto-generated constructor stub
	}



















	public ComptableDTO toComptableDTO() {
	        ModelMapper modelMapper = new ModelMapper();
	        return modelMapper.map(this, ComptableDTO.class);
	    }
	 
	 
	
	
	
	
}
