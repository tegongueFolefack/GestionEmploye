package com.example.GestionEmployes.Models;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.sql.ast.tree.expression.Collation;

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
	private int RoleId;
	
	private String Role_Name;

	@OneToMany(mappedBy = "role")
	private Collection<Utilisateur> utilisateur = new ArrayList<>();
	
	public Role(String role_Name) {
		super();
		Role_Name = role_Name;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
