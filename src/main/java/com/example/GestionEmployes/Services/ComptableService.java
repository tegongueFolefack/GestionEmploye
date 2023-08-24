package com.example.GestionEmployes.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.example.GestionEmployes.Models.Comptable;
import com.example.GestionEmployes.Repository.ComptableRepository;




@Service
public class ComptableService {
	
	 @Autowired
	    private ComptableRepository comptableRepository;
	 

		public Optional<Comptable> getComptableById(Long id) {
			return comptableRepository.findById(id);
		}

		public Iterable<Comptable> getAllComptable() {
			return comptableRepository.findAll();
		}

		public boolean deleteComptable(Long id) {
			Optional<Comptable> comptableOpt = getComptableById(id);
			if (comptableOpt.isPresent()) {
				comptableRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		}
		
		public Comptable updateComptable(Long id, Comptable comptable2) {
		    Optional<Comptable> comptableOpt = comptableRepository.findById(id);
		    
		    if (comptableOpt.isPresent()) {
		        Comptable comptable = comptableOpt.get();
		        
		        // Mise à jour des propriétés de l'objet Comptable avec les données du ComptableDTO
		        comptable.setPassWord(comptable2.getPassWord());
		        comptable.setLogin(comptable2.getLogin());
		        comptable.setDate_creation(comptable2.getDate_creation());
		        comptable.setGenre(comptable2.getGenre());
		        comptable.setEtat_Civil(comptable2.getEtat_Civil());
		        comptable.setTelephone(comptable2.getTelephone());
		        comptable.setMatricule(comptable2.getMatricule());
		        comptable.setEmail(comptable2.getEmail());
		        comptable.setCompteIBAN(comptable2.getCompteIBAN());
		        comptable.setAddresse(comptable2.getAddresse());
		        comptable.setNom(comptable2.getNom());
		        comptable.setPrenom(comptable2.getPrenom());
		        
		        // Sauvegarder les modifications dans la base de données
		        return comptableRepository.save(comptable);
		    } else {
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
		    }
		}

	 
	 public Comptable saveComptable(Comptable comptable) {
			return comptableRepository.save(comptable);
		}
}
