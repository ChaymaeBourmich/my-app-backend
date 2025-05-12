package com.example.demo.repository;



import com.example.demo.model.AppelDeFond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppelDeFondRepository extends JpaRepository<AppelDeFond, Long> {
    // 🔍 Recherche par numéro de dossier
    @Query("SELECT a FROM AppelDeFond a WHERE a.numeroDossier = :numeroDossier")
    Optional<AppelDeFond> findByNumeroDossier(@Param("numeroDossier") String numeroDossier);

    // Pour la méthode de génération de suffixe
    @Query("SELECT a FROM AppelDeFond a WHERE a.id LIKE %:suffix")
    List<AppelDeFond> findByIdEndingWith(@Param("suffix") String suffix);
    Optional<AppelDeFond> findByNumeroDossierAndNumEcheance(String numeroDossier, int numEcheance);
    Optional<AppelDeFond> findById(String id); // déjà fourni par JpaRepository








}
