package com.example.GestionEmployes.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.GestionEmployes.DTO.AvantagesDTO;
import com.example.GestionEmployes.DTO.ComptableDTO;
import com.example.GestionEmployes.DTO.PaiementDTO;
import com.example.GestionEmployes.DTO.PlanningDTO;
import com.example.GestionEmployes.DTO.UtilisateurDTO;
import com.example.GestionEmployes.Models.Avantages;
import com.example.GestionEmployes.Models.Comptable;
import com.example.GestionEmployes.Models.Paiement;
import com.example.GestionEmployes.Models.Planning;
import com.example.GestionEmployes.Models.Utilisateur;
import com.example.GestionEmployes.Services.AvantagesService;
import com.example.GestionEmployes.Services.ComptableService;
import com.example.GestionEmployes.Services.PaiementService;
import com.example.GestionEmployes.Services.PlanningService;
import com.example.GestionEmployes.Services.UtilisateurService;

@RestController
@RequestMapping("/comptable")
public class ComptableController {
	
	@Autowired
	ComptableService comptableService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private PlanningService planningService;
	
	@Autowired
	private AvantagesService avantagesService;
	
	@Autowired
	private PaiementService paiementService;

	@GetMapping("/{id}")
    public ResponseEntity<ComptableDTO> getComptableById(@PathVariable Long id) {
        try {
            Optional<Comptable> comptable = comptableService.getComptableById(id);
            if (comptable.isPresent()) {
                ComptableDTO comptableDTO = comptable.get().toComptableDTO();
                return ResponseEntity.ok(comptableDTO);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comptable not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteComptable(@PathVariable Long id) {
        try {
            comptableService.deleteComptable(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ComptableDTO>> findAll() {
        try {
            Iterable<Comptable> comptables = comptableService.getAllComptable();
            List<ComptableDTO> comptableDTOs = new ArrayList<>();
            for (Comptable comptable : comptables) {
                comptableDTOs.add(comptable.toComptableDTO());
            }
            return ResponseEntity.ok(comptableDTOs);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

	   

	
    @PostMapping("addPaiement/")
    public ResponseEntity<String> addPaiement(@RequestBody ComptableDTO comptableDto) {
        Comptable comptable = comptableDto.toComptable();
        Comptable savedComptable = comptableService.saveComptable(comptable);
        
        // You can customize the confirmation message here
        String confirmationMessage = "Comptable with ID " + savedComptable.getUserId() + " has been added successfully.";
        
        return ResponseEntity.status(HttpStatus.CREATED).body(confirmationMessage);
    }

	
	    @PutMapping("update/{id}")
	    public ResponseEntity<ComptableDTO> updateComptable(@PathVariable Long id, @RequestBody ComptableDTO comptableDto) {
	        try {
	            Optional<Comptable> comptableOpt = comptableService.getComptableById(id);
	            if (comptableOpt.isPresent()) {
	                Comptable comptable = comptableOpt.get();
	                
	                comptable = comptableDto.toComptable();
	                
	                comptable = comptableService.updateComptable(id, comptable);
	                
	                ComptableDTO comptableResponse = comptable.toComptableDTO();
	                
	                return ResponseEntity.ok(comptableResponse);
	            } else {
	                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
	            }
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }
	    
//api user
	    
	    @GetMapping("getUserById/{id}")
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
	    
	    @GetMapping("getAllUser/")
	    public ResponseEntity<List<UtilisateurDTO>> findAllUser() {
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
	    
//api planning
	    
	    @GetMapping("getPlanningById/{id}")
	    public ResponseEntity<PlanningDTO> getPlanningById(@PathVariable Integer id) {
	        try {
	            Optional<Planning> Planning = planningService.getPlanningById(id);
	            if (Planning.isPresent()) {
	            	PlanningDTO PlanningDTO = Planning.get().toPlanningDTO();
	                return ResponseEntity.ok(PlanningDTO);
	            } else {
	                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Planning not found with ID: " + id);
	            }
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }
	    
	    @GetMapping("getAllPlanning/")
	    public ResponseEntity<List<PlanningDTO>> getAllPlanning() {
	        try {
	            Iterable<Planning> Plannings = planningService.getAllPlanning();
	            List<PlanningDTO> PlanningDTOs = new ArrayList<>();
	            for (Planning Planning: Plannings) {
	            	PlanningDTOs.add(Planning.toPlanningDTO());
	            }
	            return ResponseEntity.ok(PlanningDTOs);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }
	    
//api avantages 
	    
	    @GetMapping("getAvantagesById/{id}")
	    public ResponseEntity<AvantagesDTO> getAvantagesById(@PathVariable Integer id) {
	        try {
	            Optional<Avantages> avantages = avantagesService.getAvantagesById(id);
	            if (avantages.isPresent()) {
	            	AvantagesDTO avantagesDTO = avantages.get().toAvantagesDTO();
	                return ResponseEntity.ok(avantagesDTO);
	            } else {
	                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "admin not found with ID: " + id);
	            }
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }

	    @DeleteMapping("deleteAvantages/{id}")
	    public ResponseEntity<Void> deleteAvantages(@PathVariable Integer id) {
	        try {
	        	avantagesService.deleteAvantages(id);
	            return ResponseEntity.noContent().build();
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }

	    @GetMapping("getAllAvanatages/")
	    public ResponseEntity<List<AvantagesDTO>> findAllAvanatages() {
	        try {
	            Iterable<Avantages> admins = avantagesService.getAllAvantages();
	            List<AvantagesDTO> adminDTOs = new ArrayList<>();
	            for (Avantages admin : admins) {
	            	adminDTOs.add(admin.toAvantagesDTO());
	            }
	            return ResponseEntity.ok(adminDTOs);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }

		   

		
	    @PostMapping("addAvanatages/")
	    public ResponseEntity<String> addAvantages(@RequestBody AvantagesDTO adminDto) {
	    	Avantages admin =adminDto.toAvantages();
	    	Avantages savedAvantages = avantagesService.saveAvantages(admin);
	        
	        // You can customize the confirmation message here
	        String confirmationMessage = "Comptable with ID " + savedAvantages.getId() + " has been added successfully.";
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(confirmationMessage);
	    }

		

	    @PutMapping("updateAvanatages/{id}")
	    public ResponseEntity<AvantagesDTO> updateAvantages(@PathVariable Integer id, @RequestBody AvantagesDTO AvantagesDto) {
	        try {
	            Optional<Avantages> AvantagesOpt = avantagesService.getAvantagesById(id);
	            if (AvantagesOpt.isPresent()) {
	            	Avantages avantages = AvantagesOpt.get();
	                
	            	avantages = AvantagesDto.toAvantages();
	                
	            	avantages = avantagesService.updateAvantages(id, avantages);
	                
	            	AvantagesDTO avantagesResponse = avantages.toAvantagesDTO();
	                
	                return ResponseEntity.ok(avantagesResponse);
	            } else {
	                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
	            }
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }
	    
//api paiement
	    
	    @PostMapping("add/")
	    public ResponseEntity<String> addPaiement(@RequestBody PaiementDTO employeDTOs) {
	    	Paiement employe = employeDTOs.toPaiement();
	    	Paiement savedEmploye = paiementService.savePaiement(employe);
	        
	        // You can customize the confirmation message here
	        String confirmationMessage = "Paiement with ID " + savedEmploye.getId() + " has been added successfully.";
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(confirmationMessage);
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
