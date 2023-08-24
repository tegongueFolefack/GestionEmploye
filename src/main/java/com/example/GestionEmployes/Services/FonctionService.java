package com.example.GestionEmployes.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GestionEmployes.Models.Avantages;
import com.example.GestionEmployes.Models.Departement;
import com.example.GestionEmployes.Models.Employe;
import com.example.GestionEmployes.Models.Fonction;
import com.example.GestionEmployes.Models.Planning;
import com.example.GestionEmployes.Models.TypePlanning;
import com.example.GestionEmployes.Repository.AvantagesRepository;
import com.example.GestionEmployes.Repository.DepartementRepository;
import com.example.GestionEmployes.Repository.FonctionRepository;

@Service
public class FonctionService {
	
	@Autowired
	private FonctionRepository fonctionRepository;
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private AvantagesRepository avanatagesRepository;
	
	public Iterable<Fonction> getAllFonction() {
		return fonctionRepository.findAll();
	}

	public boolean deleteFonction(Integer id) {
		Optional<Fonction> fonctionOpt = getFonctionById(id);
		if (fonctionOpt.isPresent()) {
			fonctionRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	public Fonction updatFonction(Integer id, Fonction fonctionOpt) {
	    Optional<Fonction> fonction1 = fonctionRepository.findById(id);
	    
	   
	    if (fonction1.isPresent()) {
	    	Fonction fonction = fonction1.get();
	    	fonction.setHeure_travail_semaine(fonctionOpt.getHeure_travail_semaine());
	    	fonction.setSalaire_heure(fonctionOpt.getSalaire_heure());
	    	fonction.setType_fonction(fonctionOpt.getType_fonction());
	    	
	        
	        // Sauvegarder les modifications dans la base de données
	        return fonctionRepository.save(fonction);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
	    }
	}

 
 public Fonction saveFonction(Fonction fonction) {
		return fonctionRepository.save(fonction);
	}

 public Optional<Fonction> getFonctionById(Integer id) {
		return fonctionRepository.findById(id);
		
	}
 
 public Fonction ajouterDepartementAFonction(Integer departementId, Integer fonctionId) {
     Optional<Fonction> fonctionOpt = fonctionRepository.findById(fonctionId);
     Optional<Departement> departementOpt = departementRepository.findById(departementId);

     if (fonctionOpt.isPresent() && departementOpt.isPresent()) {
         Fonction fonction = fonctionOpt.get();
         Departement departement = departementOpt.get();

         // Create a list of departments and add the single department to it
         List<Departement> departements = new ArrayList<>();
         departements.add(departement);

         // Set the list of departments for the function
         fonction.setDepartements(departements);

         return fonctionRepository.save(fonction);
     } else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Function or Departement not found");
     }
 }
 
 public Fonction ajouterAvantagesAFonction(Integer avanatagesId, Integer fonctionId) {
	    Optional<Fonction> fonctionOpt = fonctionRepository.findById(fonctionId);
	    Optional<Avantages> avantagestOpt = avanatagesRepository.findById(avanatagesId);

	    if (fonctionOpt.isPresent() && avantagestOpt.isPresent()) {
	        Fonction fonction = fonctionOpt.get();
	        Avantages avantages = avantagestOpt.get();

	        // Create a list of departments and add the single department to it
	        List<Avantages> avantagess = new ArrayList<>();
	        avantagess.add(avantages);

	        // Set the list of departments for the function
	        fonction.setAvantages(avantagess);

	        return fonctionRepository.save(fonction);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Function or Departement not found");
	    }
	}


}
