package com.example.GestionEmployes.DTO;

import java.util.ArrayList;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.GestionEmployes.Models.Comptable;




public class ComptableDTO extends UtilisateurDTO {
	
	//private Collection<Paiement> paiement =new ArrayList<>();
//	public Comptable toComptable() {
//	    Comptable comptable = new Comptable();
//	    comptable.setUserId(this.getUserId());
//	    comptable.setNom(this.getNom());
//	    comptable.setPrenom(this.getPrenom());
//	    comptable.setAddresse(this.getAddresse());
//	    comptable.setCompteIBAN(this.getCompteIBAN());
//	    comptable.setEmail(this.getEmail());
//	    comptable.setMatricule(this.getMatricule());
//	    comptable.setTelephone(this.getTelephone());
//	    comptable.setEtat_Civil(this.getEtat_Civil());
//	    comptable.setCompteIBAN(this.getCompteIBAN());
//	    comptable.setGenre(this.getGenre());
//	    comptable.setDate_creation(this.getDate_creation());
//	    comptable.setLogin(this.getLogin());
//	    comptable.setPassWord(this.getPassWord());
//	    return comptable;
//	}
	
	public Comptable toComptable() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Comptable.class);
    }
}
