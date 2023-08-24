package com.example.GestionEmployes.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GestionEmployes.Models.Comptable;
import com.example.GestionEmployes.Models.Employe;
import com.example.GestionEmployes.Repository.ComptableRepository;
import com.example.GestionEmployes.Repository.EmployeRepository;

@Service
public class EmployeService {
	
	
		
		 @Autowired
		    private EmployeRepository employeRepository;
		 

			

			public Iterable<Employe> getAllEmploye() {
				return employeRepository.findAll();
			}

			public boolean deleteEmploye(Long id) {
				Optional<Employe> comptableOpt = getEmployeById(id);
				if (comptableOpt.isPresent()) {
					employeRepository.deleteById(id);
					return true;
				} else {
					return false;
				}
			}
			
			public Employe updateEmploye(Long id, Employe employeOpt) {
			    Optional<Employe> employe1 = employeRepository.findById(id);
			    
			    if (employe1.isPresent()) {
			    	Employe employe = employe1.get();
			    	employe.setPassWord(employeOpt.getPassWord());
			    	employe.setLogin(employeOpt.getLogin());
			    	employe.setDate_creation(employeOpt.getDate_creation());
			    	employe.setGenre(employeOpt.getGenre());
			    	employe.setEtat_Civil(employeOpt.getEtat_Civil());
			    	employe.setTelephone(employeOpt.getTelephone());
			    	employe.setMatricule(employeOpt.getMatricule());
			    	employe.setEmail(employeOpt.getEmail());
			    	employe.setCompteIBAN(employeOpt.getCompteIBAN());
			    	employe.setAddresse(employeOpt.getAddresse());
			    	employe.setNom(employeOpt.getNom());
			    	employe.setPrenom(employeOpt.getPrenom());
			        
			        // Sauvegarder les modifications dans la base de données
			        return employeRepository.save(employe);
			    } else {
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
			    }
			}

		 
		 public Employe saveEmploye(Employe employe) {
				return employeRepository.save(employe);
			}

		 public Optional<Employe> getEmployeById(Long id) {
				return employeRepository.findById(id);
				
			}
}

	

	

	

	
	
	
