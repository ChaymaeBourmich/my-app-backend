package com.example.demo.repository;



import com.example.demo.model.SRG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SRGRepository extends JpaRepository<SRG, String> {
    // Renvoie les numéros de dossiers associés à un numéro client donné
    @Query("SELECT s.dossier FROM SRG_DB s WHERE s.numeroClient = :numeroClient")
    List<String> findDossierByNumeroClient(@Param("numeroClient") String numeroClient);

    // Renvoie tous les numéros client distincts
    @Query("SELECT DISTINCT s.numeroClient FROM SRG_DB s")
    List<String> findAllDistinctNumeroClients();

    Optional<SRG> findByDossier(String dossier);

}
