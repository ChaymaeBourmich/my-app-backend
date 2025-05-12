package com.example.demo.repository;

import com.example.demo.DTO.AdminDashDTO;
import com.example.demo.DTO.RecouvrementDTO;
import com.example.demo.DTO.RecouvrementProjection;
import com.example.demo.model.AppelDeFond;
import com.example.demo.model.Recouvrement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecouvrementRepository extends JpaRepository<Recouvrement, Long> {

    // ✅ Requête native corrigée avec crochets et noms corrects (à adapter si tu modifies ta base)
    @Query(value = "SELECT " +
            "r.ID, " +
            "r.[Adresse client], " +
            "r.[Date affectation], " +
            "r.[Désignation de la division], " +
            "r.[N° Dossier], " +
            "r.[Date fin], " +
            "r.Echéance, " +
            "r.Delai, " +
            "r.Client, " +
            "r.[Ancien numéro de produit], " +
            "r.[Désignation du projet], " +
            "r.Montant, " +
            "r.[Prix de Cession], " +
            "r.Projet, " +
            "r.[N° Téléphone], " +
            "s.[Numéro Client] AS numcli " +  // 🔥 alias simple sans majuscule
            "FROM Appel_Fond_DB r " +
            "JOIN SRG_DB s ON RTRIM(LTRIM(r.[N° Dossier])) = RTRIM(LTRIM(s.dossier))",  // 🧼 évite les espaces cachés
            nativeQuery = true)
    List<Object[]> findRecouvrementsWithNumeroClient();

    List<Recouvrement> findByDossierIn(List<String> dossierNumbers);

    List<Recouvrement> findAllByDossierIn(List<String> dossiers);


    @Query(value = "SELECT " +
            "r.ID, " +
            "r.[Adresse client], " +
            "r.[Date affectation], " +
            "r.[Désignation de la division], " +
            "r.[N° Dossier], " +
            "r.[Date fin], " +
            "r.Echéance, " +
            "r.Delai, " +
            "r.Client, " +
            "r.[Ancien numéro de produit], " +
            "r.[Désignation du projet], " +
            "r.Montant, " +
            "r.[Prix de Cession], " +
            "r.Projet, " +
            "r.[N° Téléphone], " +
            "s.[Numéro Client] AS numcli " +
            "FROM Appel_Fond_DB r " +
            "JOIN SRG_DB s ON RTRIM(LTRIM(r.[N° Dossier])) = RTRIM(LTRIM(s.dossier)) " +
            "JOIN Operation o ON o.code_operation = s.[Opération] " +
            "WHERE o.utilisateur_id = :userId",
            nativeQuery = true)
    List<Object[]> findRecouvrementsByUserId(@Param("userId") Long userId);



    @Query("SELECT SUM(r.prix_cession) FROM Recouvrement r")
    Double sumMontantGlobal();


}


