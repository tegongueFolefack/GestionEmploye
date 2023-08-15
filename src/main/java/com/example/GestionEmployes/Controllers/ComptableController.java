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

import com.example.GestionEmployes.DTO.ComptableDTO;
import com.example.GestionEmployes.Models.Comptable;
import com.example.GestionEmployes.Services.ComptableService;

@RestController
@RequestMapping("/comptable")
public class ComptableController {
	
	@Autowired
	ComptableService comptableService;

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

	   

	
    @PostMapping("add/")
    public ResponseEntity<String> addUtilisateur(@RequestBody ComptableDTO comptableDto) {
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
	    	
	    @ExceptionHandler(ResponseStatusException.class)
	    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
	        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }

}
