package com.example.GestionEmployes.Models;





import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.example.GestionEmployes.DTO.AdminDTO;
import com.example.GestionEmployes.DTO.UtilisateurDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE" )
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userId;
	
	private String passWord;
	private String login;
	private Date date_creation;
	private String genre;
	private String etat_Civil;
	private int telephone;
	private int matricule;
	private String email;
	private String compteIBAN;
	private String addresse;
	private String nom;
	private String prenom;
	private double salaireBase;
	private double tauxHoraire;
	private int heuresTravailFixes;
	private boolean transportPrive;
	
	@OneToMany(mappedBy = "utilisateur")
    private List<HeureSupplementaire> heuresSupplementaires;

   
	
	@OneToMany(mappedBy = "utilisateur")
	private Collection<Paiement>paiement = new ArrayList<>();
	
	@ManyToOne
	private Departement departement; 
	
	@OneToMany(mappedBy = "utilisateur")
	private Collection<Planning> Planning =new ArrayList<>();
	
	@ManyToOne
	private Role role;
	
	public Utilisateur() {
		super();
		
	}

	
	
	
	
	public UtilisateurDTO toUtilisateurDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, UtilisateurDTO.class);
    }





	public Utilisateur(Long userId, String passWord, String login, Date date_creation, String genre, String etat_Civil,
			int telephone, int matricule, String email, String compteIBAN, String addresse, String nom, String prenom,
			double salaireBase, double tauxHoraire, int heuresTravailFixes, boolean transportPrive) {
		super();
		this.userId = userId;
		this.passWord = passWord;
		this.login = login;
		this.date_creation = date_creation;
		this.genre = genre;
		this.etat_Civil = etat_Civil;
		this.telephone = telephone;
		this.matricule = matricule;
		this.email = email;
		this.compteIBAN = compteIBAN;
		this.addresse = addresse;
		this.nom = nom;
		this.prenom = prenom;
		this.salaireBase = salaireBase;
		this.tauxHoraire = tauxHoraire;
		this.heuresTravailFixes = heuresTravailFixes;
		this.transportPrive = transportPrive;
	}

	




	
	
	
	

}
