package com.example.GestionEmployes.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.GestionEmployes.Models.Paiement;

public interface PaiementRepository extends CrudRepository<Paiement, Integer> {

}
