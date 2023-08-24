package com.example.GestionEmployes.Models;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.DTO.PlanningDTO;
import com.example.GestionEmployes.DTO.RoleDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	private String role_Name;

	@OneToMany(mappedBy = "role")
	private Collection<Utilisateur> utilisateur = new ArrayList<>();
	
	

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RoleDTO toRoleDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, RoleDTO.class);
    }

	public Role(int id, String role_Name) {
		super();
		this.id = id;
		this.role_Name = role_Name;
		
	}

	
	
}
