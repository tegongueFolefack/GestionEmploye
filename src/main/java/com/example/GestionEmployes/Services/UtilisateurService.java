package com.example.GestionEmployes.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GestionEmployes.Models.Departement;
import com.example.GestionEmployes.Models.Role;
import com.example.GestionEmployes.Models.Utilisateur;
import com.example.GestionEmployes.Repository.DepartementRepository;
import com.example.GestionEmployes.Repository.RoleRepository;
import com.example.GestionEmployes.Repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	
	 @Autowired
	    private UtilisateurRepository utilisateurRepository;
	 
	 @Autowired
	 private RoleRepository roleRepository;
	 
	 @Autowired DepartementRepository departementRepository;
	 

		public Optional<Utilisateur> getUtilisateurById(Long id) {
			return utilisateurRepository.findById(id);
		}

		public Iterable<Utilisateur> getAllUtilisateur() {
			return utilisateurRepository.findAll();
		}

		public boolean deleteUtilisateur(Long id) {
			Optional<Utilisateur> utilisateurOpt = getUtilisateurById(id);
			if (utilisateurOpt.isPresent()) {
				utilisateurRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		}
		
		public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur2) {
		    Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(id);
		    
		    if (utilisateurOpt.isPresent()) {
		    	Utilisateur utilisateur = utilisateurOpt.get();
		        
		        // Mise à jour des propriétés de l'objet Comptable avec les données du ComptableDTO
		    	utilisateur.setPassWord(utilisateur2.getPassWord());
		    	utilisateur.setLogin(utilisateur2.getLogin());
		    	utilisateur.setDate_creation(utilisateur2.getDate_creation());
		    	utilisateur.setGenre(utilisateur2.getGenre());
		    	utilisateur.setEtat_Civil(utilisateur2.getEtat_Civil());
		    	utilisateur.setTelephone(utilisateur2.getTelephone());
		    	utilisateur.setMatricule(utilisateur2.getMatricule());
		    	utilisateur.setEmail(utilisateur2.getEmail());
		    	utilisateur.setCompteIBAN(utilisateur2.getCompteIBAN());
		    	utilisateur.setAddresse(utilisateur2.getAddresse());
		    	utilisateur.setNom(utilisateur2.getNom());
		    	utilisateur.setPrenom(utilisateur2.getPrenom());
		        
		        // Sauvegarder les modifications dans la base de données
		        return utilisateurRepository.save(utilisateur);
		    } else {
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
		    }
		}

	 
	 public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
			return utilisateurRepository.save(utilisateur);
		}
	 
	 
	 public Utilisateur ajouterDepartementAUtilisateur(Long utilisateurId, Integer departementId) {
	        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
	        Optional<Departement> departementOpt = departementRepository.findById(departementId);

	        if (utilisateurOpt.isPresent() && departementOpt.isPresent()) {
	            Utilisateur utilisateur = utilisateurOpt.get();
	            Departement departement = departementOpt.get();

	            utilisateur.setDepartement(departement);
	            return utilisateurRepository.save(utilisateur);
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur or Departement not found");
	        }
	    }

	    public Utilisateur ajouterRoleAUtilisateur(Long utilisateurId, Integer roleId) {
	        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
	        Optional<Role> roleOpt = roleRepository.findById(roleId);

	        if (utilisateurOpt.isPresent() && roleOpt.isPresent()) {
	            Utilisateur utilisateur = utilisateurOpt.get();
	            Role role = roleOpt.get();

	            utilisateur.setRole(role);
	            return utilisateurRepository.save(utilisateur);
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur or Role not found");
	        }
		}

		

}
