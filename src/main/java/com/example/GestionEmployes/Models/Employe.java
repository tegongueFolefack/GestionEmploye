package com.example.GestionEmployes.Models;



import java.util.Date;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.DTO.ComptableDTO;
import com.example.GestionEmployes.DTO.EmployeDTO;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "employe")
@DiscriminatorValue("employe")
public class Employe extends Utilisateur {
	
	
	
	
	public Employe() {
		super();
		
	}

	
	





	public Employe(Long userId, String passWord, String login, Date date_creation, String genre, String etat_Civil,
			int telephone, int matricule, String email, String compteIBAN, String addresse, String nom, String prenom,
			double salaireBase, double tauxHoraire, int heuresTravailFixes, boolean transportPrive) {
		super(userId, passWord, login, date_creation, genre, etat_Civil, telephone, matricule, email, compteIBAN, addresse, nom,
				prenom, salaireBase, tauxHoraire, heuresTravailFixes, transportPrive);
		// TODO Auto-generated constructor stub
	}








	public EmployeDTO toEmployeDTO() {
	        ModelMapper modelMapper = new ModelMapper();
	        return modelMapper.map(this, EmployeDTO.class);
	    }
	
	

}
