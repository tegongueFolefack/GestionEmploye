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

import com.example.GestionEmployes.DTO.EmployeDTO;
import com.example.GestionEmployes.DTO.PlanningDTO;
import com.example.GestionEmployes.Models.Employe;
import com.example.GestionEmployes.Models.Planning;
import com.example.GestionEmployes.Services.EmployeService;
import com.example.GestionEmployes.Services.PlanningService;



@RestController
@RequestMapping("/employe")
public class EmployeController {
	
	@Autowired
	private EmployeService employeService;
	
	@Autowired
	private PlanningService planningService;
	

		@GetMapping("/{id}")
	    public ResponseEntity<EmployeDTO> getEmployeById(@PathVariable Long id) {
	        try {
	            Optional<Employe> employe = employeService.getEmployeById(id);
	            if (employe.isPresent()) {
	            	EmployeDTO employeDTO = employe.get().toEmployeDTO();
	                return ResponseEntity.ok(employeDTO);
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
	        	employeService.deleteEmploye(id);
	            return ResponseEntity.noContent().build();
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }

	    @GetMapping("/")
	    public ResponseEntity<List<EmployeDTO>> findAll() {
	        try {
	            Iterable<Employe> employes = employeService.getAllEmploye();
	            List<EmployeDTO> employeDTOs = new ArrayList<>();
	            for (Employe employe : employes) {
	            	employeDTOs.add(employe.toEmployeDTO());
	            }
	            return ResponseEntity.ok(employeDTOs);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
	        }
	    }

		   

		
	    @PostMapping("add/")
	    public ResponseEntity<String> addUtilisateur(@RequestBody EmployeDTO employeDTOs) {
	    	Employe employe = employeDTOs.toEmploye();
	    	Employe savedEmploye = employeService.saveEmploye(employe);
	        
	        // You can customize the confirmation message here
	        String confirmationMessage = "Employe with ID " + savedEmploye.getUserId() + " has been added successfully.";
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(confirmationMessage);
	    }

		
		    @PutMapping("update/{id}")
		    public ResponseEntity<EmployeDTO> updateEmploye(@PathVariable Long id, @RequestBody EmployeDTO employeDto) {
		        try {
		            Optional<Employe> employeOpt = employeService.getEmployeById(id);
		            if (employeOpt.isPresent()) {
		            	Employe employe = employeOpt.get();
		                
		            	employe = employeDto.toEmploye();
		                
		            	employe = employeService.updateEmploye(id, employe);
		                
		                EmployeDTO employeResponse = employe.toEmployeDTO();
		                
		                return ResponseEntity.ok(employeResponse);
		            } else {
		                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
		            }
		        } catch (Exception e) {
		            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
		        }
		    }
		    
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
		    	
		    @ExceptionHandler(ResponseStatusException.class)
		    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
		        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
		    }

		    @ExceptionHandler(Exception.class)
		    public ResponseEntity<String> handleException(Exception ex) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		    }

	}



