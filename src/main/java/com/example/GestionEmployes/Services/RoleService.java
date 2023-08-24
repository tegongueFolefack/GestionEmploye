package com.example.GestionEmployes.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GestionEmployes.Models.Departement;
import com.example.GestionEmployes.Models.Role;
import com.example.GestionEmployes.Repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Iterable<Role> getAllRole() {
		return roleRepository.findAll();
	}

	public boolean deleteRole(Integer id) {
		Optional<Role> RoleOpt = getRoleById(id);
		if (RoleOpt.isPresent()) {
			roleRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	public Role updateRole(Integer id, Role RoleOpt) {
	    Optional<Role> Role1 = roleRepository.findById(id);
	    
	    if (Role1.isPresent()) {
	    	Role Role = Role1.get();
	    	Role.setRole_Name(RoleOpt.getRole_Name());
	    	
	        
	        // Sauvegarder les modifications dans la base de données
	        return roleRepository.save(Role);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
	    }
	}

 
 public Role saveRole(Role Role) {
		return roleRepository.save(Role);
	}

 public Optional<Role> getRoleById(Integer id) {
		return roleRepository.findById(id);
		
	}


}
