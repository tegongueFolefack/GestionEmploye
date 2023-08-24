package com.example.GestionEmployes.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GestionEmployes.Models.Admin;
import com.example.GestionEmployes.Models.Departement;
import com.example.GestionEmployes.Models.Employe;
import com.example.GestionEmployes.Models.Fonction;
import com.example.GestionEmployes.Models.Utilisateur;
import com.example.GestionEmployes.Repository.AdminRepository;
import com.example.GestionEmployes.Repository.DepartementRepository;
import com.example.GestionEmployes.Repository.FonctionRepository;

@Service
public class DepartementService {
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private FonctionRepository fonctionRepository;
	
	public Iterable<Departement> getAllDepartement() {
		return departementRepository.findAll();
	}

	public boolean deleteDepartement(Integer id) {
		Optional<Departement> DepartementOpt = getDepartementById(id);
		if (DepartementOpt.isPresent()) {
			departementRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	public Departement updateDepartement(Integer id, Departement DepartementOpt) {
	    Optional<Departement> Departement1 = departementRepository.findById(id);
	    
	    if (Departement1.isPresent()) {
	    	Departement Departement = Departement1.get();
	    	Departement.setNom_departement(DepartementOpt.getNom_departement());
	    	
	        
	        // Sauvegarder les modifications dans la base de données
	        return departementRepository.save(Departement);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
	    }
	}

 
 public Departement saveDepartement(Departement Departement) {
		return departementRepository.save(Departement);
	}

 public Optional<Departement> getDepartementById(Integer id) {
		return departementRepository.findById(id);
		
	}
 
 public Departement ajouterFonctionADepartement(Integer departementId, Integer fonctionId) {
	    Optional<Fonction> fonctionOpt = fonctionRepository.findById(fonctionId);
	    Optional<Departement> departementOpt = departementRepository.findById(departementId);

	    if (fonctionOpt.isPresent() && departementOpt.isPresent()) {
	        Fonction fonction = fonctionOpt.get();
	        Departement departement = departementOpt.get();

	        // Create a list of FONCTIONS and add the single fonction to it
	        List<Fonction> fonctions = new ArrayList<>();
	        fonctions.add(fonction);

	        // Set the list of departments for the function
	        departement.setFonction(fonctions); ;

	        return departementRepository.save(departement);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Function or Departement not found");
	    }
	}
 
 public Departement ajouterAdminADepartement(Long adminId, Integer departementId) {
     Optional<Admin> adminOpt = adminRepository.findById(adminId);
     Optional<Departement> departementOpt = departementRepository.findById(departementId);

     if (adminOpt.isPresent() && departementOpt.isPresent()) {
         Admin admin = adminOpt.get();
         Departement departement = departementOpt.get();

         departement.setAdmin(admin);
         return departementRepository.save(departement);
     } else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "admin or Departement not found");
     }
 }

}
