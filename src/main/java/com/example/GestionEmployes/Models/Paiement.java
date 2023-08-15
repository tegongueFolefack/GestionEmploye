package com.example.GestionEmployes.Models;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Paiement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double Total_heure;
	private Date Date_paiement;
	private Date Periode_paiement;
	private double Precompte_professionnel;
	private double Cotisation_ONSS;
	private double Retenue_Retraite;
	private double Retenue_Chaumage;
	private double Retenu_total;
	private double ONSS_Renum;
	private double Suppl_transport;
	private double Primes_HS;
	private double Prime_Prestation;
	private double Prime_Equipe;
	private double Total_prime;
	private double Total_brut;
	private double Total_imposable;
	private double Total_Net;
	
	@ManyToOne
	private Admin admin;
	
	@ManyToOne
	private Utilisateur utilisateur;
	
	@ManyToOne
	private Comptable comptable;
	
	public Paiement(double total_heure, Date date_paiement, Date periode_paiement, double precompte_professionnel,
			double cotisation_ONSS, double retenue_Retraite, double retenue_Chaumage, double retenu_total,
			double oNSS_Renum, double suppl_transport, double primes_HS, double prime_Prestation, double prime_Equipe,
			double total_prime, double total_brut, double total_imposable, double total_Net) {
		super();
		Total_heure = total_heure;
		Date_paiement = date_paiement;
		Periode_paiement = periode_paiement;
		Precompte_professionnel = precompte_professionnel;
		Cotisation_ONSS = cotisation_ONSS;
		Retenue_Retraite = retenue_Retraite;
		Retenue_Chaumage = retenue_Chaumage;
		Retenu_total = retenu_total;
		ONSS_Renum = oNSS_Renum;
		Suppl_transport = suppl_transport;
		Primes_HS = primes_HS;
		Prime_Prestation = prime_Prestation;
		Prime_Equipe = prime_Equipe;
		Total_prime = total_prime;
		Total_brut = total_brut;
		Total_imposable = total_imposable;
		Total_Net = total_Net;
	}


	public Paiement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
