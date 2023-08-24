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

import com.example.GestionEmployes.DTO.RoleDTO;
import com.example.GestionEmployes.Models.Role;
import com.example.GestionEmployes.Services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Integer id) {
        try {
            Optional<Role> Role = roleService.getRoleById(id);
            if (Role.isPresent()) {
            	RoleDTO RoleDTO = Role.get().toRoleDTO();
                return ResponseEntity.ok(RoleDTO);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        try {
        	roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<RoleDTO>> findAll() {
        try {
            Iterable<Role> Roles = roleService.getAllRole();
            List<RoleDTO> RoleDTOs = new ArrayList<>();
            for (Role Role: Roles) {
            	RoleDTOs.add(Role.toRoleDTO());
            }
            return ResponseEntity.ok(RoleDTOs);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

	   

	
    @PostMapping("add/")
    public ResponseEntity<String> addRole(@RequestBody RoleDTO RoleDto) {
    	Role Role = RoleDto.toRole();
    	Role savedRole = roleService.saveRole(Role);
        
        // You can customize the confirmation message here
        String confirmationMessage = "Role with ID " + savedRole.getId() + " has been added successfully.";
        
        return ResponseEntity.status(HttpStatus.CREATED).body(confirmationMessage);
    }

	
	    @PutMapping("update/{id}")
	    public ResponseEntity<RoleDTO> updateFonction(@PathVariable Integer id, @RequestBody RoleDTO RoleDto) {
	        try {
	            Optional<Role> RoleOpt = roleService.getRoleById(id);
	            if (RoleOpt.isPresent()) {
	            	Role Role = RoleOpt.get();
	                
	            	Role = RoleDto.toRole();
	                
	            	Role = roleService.updateRole(id, Role);
	                
	            	RoleDTO RoleResponse = Role.toRoleDTO();
	                
	                return ResponseEntity.ok(RoleResponse);
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
