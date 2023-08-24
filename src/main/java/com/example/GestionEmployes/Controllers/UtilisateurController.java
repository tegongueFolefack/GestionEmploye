package com.example.GestionEmployes.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.GestionEmployes.DTO.UtilisateurDTO;
import com.example.GestionEmployes.Models.Departement;
import com.example.GestionEmployes.Models.Role;
import com.example.GestionEmployes.Models.Utilisateur;
import com.example.GestionEmployes.Services.UtilisateurService;


@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable Long id) {
        try {
            Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
            if (utilisateur.isPresent()) {
            	UtilisateurDTO utilisateurDTO = utilisateur.get().toUtilisateurDTO();
                return ResponseEntity.ok(utilisateurDTO);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "utilisateur not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        try {
        	utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<UtilisateurDTO>> findAll() {
        try {
            Iterable<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateur();
            List<UtilisateurDTO> utilisateurDTOs = new ArrayList<>();
            for (Utilisateur utilisateur : utilisateurs) {
            	utilisateurDTOs.add(utilisateur.toUtilisateurDTO());
            }
            return ResponseEntity.ok(utilisateurDTOs);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

	   

	
    @PostMapping("add/")
    public ResponseEntity<String> addUtilisateur(@RequestBody UtilisateurDTO utilisateurDto) {
    	Utilisateur utilisateur =utilisateurDto.toUtilisateur();
    	Utilisateur savedUtilisateur = utilisateurService.saveUtilisateur(utilisateur);
        
        // You can customize the confirmation message here
        String confirmationMessage = "Utilisateur with ID " + savedUtilisateur.getUserId() + " has been added successfully.";
        
        return ResponseEntity.status(HttpStatus.CREATED).body(confirmationMessage);
    }

	
	    @PutMapping("update/{id}")
	    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDto) {
	        try {
	            Optional<Utilisateur> utilisateurOpt = utilisateurService.getUtilisateurById(id);
	            if (utilisateurOpt.isPresent()) {
	            	Utilisateur utilisateur = utilisateurOpt.get();
	                
	            	utilisateur = utilisateurDto.toUtilisateur();
	                
	            	utilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
	                
	                UtilisateurDTO utilisateurResponse = utilisateur.toUtilisateurDTO();
	                
	                return ResponseEntity.ok(utilisateurResponse);
	            } else {
	                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
	            }
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }
	    
	    @PostMapping("/{utilisateurId}/departements/{departementId}")
	    public ResponseEntity<UtilisateurDTO> ajouterDepartementAUtilisateur(@PathVariable Long utilisateurId, @PathVariable Integer departementId) {
	        try {
	            Utilisateur utilisateur = utilisateurService.ajouterDepartementAUtilisateur(utilisateurId, departementId);
	            UtilisateurDTO utilisateurDTO = utilisateur.toUtilisateurDTO();
	            return ResponseEntity.ok(utilisateurDTO);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }

	    @PostMapping("/{utilisateurId}/roles/{roleId}")
	    public ResponseEntity<UtilisateurDTO> ajouterRoleAUtilisateur(@PathVariable Long utilisateurId, @PathVariable Integer roleId) {
	        try {
	            Utilisateur utilisateur = utilisateurService.ajouterRoleAUtilisateur(utilisateurId, roleId);
	            UtilisateurDTO utilisateurDTO = utilisateur.toUtilisateurDTO();
	            return ResponseEntity.ok(utilisateurDTO);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }
	    	
	    @ExceptionHandler(ResponseStatusException.class)
	    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
	        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }


}
