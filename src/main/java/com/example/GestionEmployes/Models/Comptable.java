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

	public Comptable(String nom, String prenom, String passWord, String login, Date date_creation, String genre,
			String etat_Civil, int telephone, int matricule, String email, String compteIBAN, String addresse) {
		super(nom, prenom, passWord, login, date_creation, genre, etat_Civil, telephone, matricule, email, compteIBAN,
				addresse);
		
	}
	
//	public ComptableDTO toComptableDTO() {
//	    ComptableDTO comptableDto = new ComptableDTO();
//	    comptableDto.setUserId(this.getUserId());
//	    comptableDto.setNom(this.getNom());
//	    comptableDto.setPrenom(this.getPrenom());
//	    comptableDto.setAddresse(this.getAddresse());
//	    comptableDto.setCompteIBAN(this.getCompteIBAN());
//	    comptableDto.setEmail(this.getEmail());
//	    comptableDto.setMatricule(this.getMatricule());
//	    comptableDto.setTelephone(this.getTelephone());
//	    comptableDto.setEtat_Civil(this.getEtat_Civil());
//	    comptableDto.setCompteIBAN(this.getCompteIBAN());
//	    comptableDto.setGenre(this.getGenre());
//	    comptableDto.setDate_creation(this.getDate_creation());
//	    comptableDto.setLogin(this.getLogin());
//	    comptableDto.setPassWord(this.getPassWord());
//
//	    return comptableDto;
//	}
	 
	 public ComptableDTO toComptableDTO() {
	        ModelMapper modelMapper = new ModelMapper();
	        return modelMapper.map(this, ComptableDTO.class);
	    }
	 
	 
	
	
	
	
}
