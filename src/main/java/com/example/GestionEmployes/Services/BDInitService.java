package com.example.GestionEmployes.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.GestionEmployes.Models.Role;
import com.example.GestionEmployes.Models.Utilisateur;
import com.example.GestionEmployes.Repository.RoleRepository;
import com.example.GestionEmployes.Repository.UtilisateurRepository;

@Service
public class BDInitService {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	 @Autowired
	 private RoleRepository roleRepository;

	 
	    
	    public void initializeUsers() {
	    	  Role adminRole = new Role();
	          adminRole.setRole_Name("ROLE_ADMIN");
	          roleRepository.save(adminRole);

	          Role comptableRole = new Role();
	          comptableRole.setRole_Name("ROLE_COMPTABLE");
	          roleRepository.save(comptableRole);

	          Role employeRole = new Role();
	          employeRole.setRole_Name("ROLE_EMPLOYE");
	          roleRepository.save(employeRole);

	          
	          Utilisateur admin = new Utilisateur();
	          admin.setLogin("admin@example.com"); 
	          admin.setPassWord("adminpassword"); 
	          admin.setDate_creation(new Date()); 
	          admin.setGenre("Homme"); 
	          admin.setEtat_Civil("Célibataire"); 
	          admin.setTelephone(123456789); 
	          admin.setMatricule(123); 
	          admin.setEmail("admin@example.com");
	          admin.setCompteIBAN("BE123456789"); 
	          admin.setAddresse("123, Rue Principale"); 
	          admin.setNom("Admin");
	          admin.setPrenom("Admin"); 
	          admin.setRole(adminRole); 
	          utilisateurRepository.save(admin); 
	          
	          Utilisateur comptable = new Utilisateur();
	          comptable.setLogin("comptable@example.com"); 
	          comptable.setPassWord("comptablepassword"); 
	          comptable.setDate_creation(new Date()); 
	          comptable.setGenre("Homme"); 
	          comptable.setEtat_Civil("Célibataire"); 
	          comptable.setTelephone(123456789); 
	          comptable.setMatricule(123); 
	          comptable.setEmail("comptable@example.com");
	          comptable.setCompteIBAN("BE123456789"); 
	          comptable.setAddresse("123, Rue Principale"); 
	          comptable.setNom("comptable");
	          comptable.setPrenom("comptable"); 
	          comptable.setRole(comptableRole); 
	          utilisateurRepository.save(comptable); 
	          
	          
	          Utilisateur employe = new Utilisateur();
	          employe.setLogin("employe@example.com"); 
	          employe.setPassWord("employepassword"); 
	          employe.setDate_creation(new Date()); 
	          employe.setGenre("Homme"); 
	          employe.setEtat_Civil("Célibataire"); 
	          employe.setTelephone(123456789); 
	          employe.setMatricule(123); 
	          employe.setEmail("employe@example.com");
	          employe.setCompteIBAN("BE123456789"); 
	          employe.setAddresse("123, Rue Principale"); 
	          employe.setNom("employe");
	          employe.setPrenom("employe"); 
	          employe.setRole(employeRole); 
	          utilisateurRepository.save(employe); 
	    }

}
