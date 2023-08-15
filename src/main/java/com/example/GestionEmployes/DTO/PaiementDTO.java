package com.example.GestionEmployes.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class PaiementDTO {

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
}
