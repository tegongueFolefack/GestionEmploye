package com.example.GestionEmployes.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.example.GestionEmployes.DTO.AdminDTO;
import com.example.GestionEmployes.DTO.AvantagesDTO;
import com.example.GestionEmployes.DTO.EmployeDTO;
import com.example.GestionEmployes.Models.Admin;
import com.example.GestionEmployes.Models.Avantages;
import com.example.GestionEmployes.Models.Employe;
import com.example.GestionEmployes.Services.AdminService;
import com.example.GestionEmployes.Services.AvantagesService;

@RestController
@RequestMapping("/avantages")
public class AvantagesController {
	
	@Autowired
	private AvantagesService avantagesService;

	@GetMapping("/{id}")
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAvantages(@PathVariable Integer id) {
        try {
        	avantagesService.deleteAvantages(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<AvantagesDTO>> findAll() {
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

	   

	
    @PostMapping("add/")
    public ResponseEntity<String> addAvantages(@RequestBody AvantagesDTO adminDto) {
    	Avantages admin =adminDto.toAvantages();
    	Avantages savedAvantages = avantagesService.saveAvantages(admin);
        
        // You can customize the confirmation message here
        String confirmationMessage = "Comptable with ID " + savedAvantages.getId() + " has been added successfully.";
        
        return ResponseEntity.status(HttpStatus.CREATED).body(confirmationMessage);
    }

	

    @PutMapping("update/{id}")
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
	    	
	    @ExceptionHandler(ResponseStatusException.class)
	    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
	        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }


}
