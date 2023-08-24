package com.example.GestionEmployes.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GestionEmployes.Models.Admin;
import com.example.GestionEmployes.Repository.AdminRepository;

@Service
public class AdminService {
	
	 @Autowired
	    private AdminRepository AdminRepository;
	 

		public Optional<Admin> getAdminById(Long id) {
			return AdminRepository.findById(id);
		}

		public Iterable<Admin> getAllAdmin() {
			return AdminRepository.findAll();
		}

		public boolean deleteAdmin(Long id) {
			Optional<Admin> adminOpt = getAdminById(id);
			if (adminOpt.isPresent()) {
				AdminRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		}
		
		public Admin updateAdmin(Long id, Admin admin) {
		    Optional<Admin> adminOpt = AdminRepository.findById(id);
		    
		    if (adminOpt.isPresent()) {
		    	Admin admin1 = adminOpt.get();
		        
		        // Mise à jour des propriétés de l'objet Comptable avec les données du ComptableDTO
		    	admin1.setPassWord(admin.getPassWord());
		    	admin1.setLogin(admin.getLogin());
		    	admin1.setDate_creation(admin.getDate_creation());
		    	admin1.setGenre(admin.getGenre());
		    	admin1.setEtat_Civil(admin.getEtat_Civil());
		    	admin1.setTelephone(admin.getTelephone());
		    	admin1.setMatricule(admin.getMatricule());
		    	admin1.setEmail(admin.getEmail());
		    	admin1.setCompteIBAN(admin.getCompteIBAN());
		    	admin1.setAddresse(admin.getAddresse());
		    	admin1.setNom(admin.getNom());
		    	admin1.setPrenom(admin.getPrenom());
		        
		        // Sauvegarder les modifications dans la base de données
		        return AdminRepository.save(admin1);
		    } else {
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
		    }
		}

	 
	 public Admin saveAdmin(Admin admin) {
			return AdminRepository.save(admin);
		}

}
