package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    // Méthode personnalisée pour rechercher un client par CIN
    Optional<Client> findByNom(String nom);
    Optional<Client> findByCin(String cin);
    Optional<Client> findByNumeroClient(String numeroClient);

}
