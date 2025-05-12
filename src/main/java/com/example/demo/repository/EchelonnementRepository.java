package com.example.demo.repository;

import com.example.demo.model.Echelonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EchelonnementRepository extends JpaRepository<Echelonnement, Long> {
    // Requête pour récupérer tous les échelonnements
    List<Echelonnement> findByNumeroDossier(String numeroDossier);

    // Vous pouvez également ajouter d'autres méthodes selon vos besoins
}
