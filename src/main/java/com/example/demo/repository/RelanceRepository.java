package com.example.demo.repository;

import com.example.demo.DTO.RelanceAvecCINProjection;
import com.example.demo.DTO.RelanceParMoisDTO;
import com.example.demo.model.Relance;
import com.example.demo.model.User;
import com.example.demo.model.UtilisateurDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface RelanceRepository extends JpaRepository<Relance, String> {

    List<Relance> findByUserId(Long userId);


    List<Relance> findByUser(User user);


    @Query("SELECT SUM(r.montantAction) FROM Relance r WHERE r.user = :user")
    Double sumMontantByUser(@Param("user") User user);




    @Query("SELECT r FROM Relance r WHERE r.user.id = :userId")
    List<Relance> findRelancesByUserId(@Param("userId") Long userId);

//********************************************************************************************

@Query(value = "SELECT r.*, s.[CIN/RC] AS cin " +
        "FROM Relances r " +
        "JOIN SRG_DB s ON s.dossier = r.numero_dossier " +
        "WHERE r.numero_relance = ( " +
        "    SELECT MAX(r2.numero_relance) " +
        "    FROM Relances r2 " +
        "    WHERE r2.numero_dossier = r.numero_dossier " +
        ") AND r.user_id = :userId",
        nativeQuery = true)

List<RelanceAvecCINProjection> findLastRelanceByDossierForUserWithCIN(@Param("userId") Long userId);

//********************************************************************************************


    @Query("SELECT DISTINCT r.numeroDossier FROM Relance r WHERE r.numeroRelance >= 3")
    List<String> findDossierNumbersWithAtLeastThreeRelances();

//********************************************************************************************

    //Relance findTopByClientIdOrderByNumeroRelanceDesc(Long clientId);
    @Query("SELECT DISTINCT r.numeroDossier FROM Relance r " +
            "JOIN SRG_DB s ON r.numeroDossier = s.dossier " +
            "WHERE s.titreFoncier IS NOT NULL AND s.titreFoncier <> '' " +
            "GROUP BY r.numeroDossier, s.titreFoncier " +
            "HAVING COUNT(r.id) > 3")
    List<String> findDossierNumbersWithMoreThan3RelancesAndTF();

//********************************************************************************************



    @Query("SELECT COALESCE(MAX(r.numeroRelance), 0) FROM Relance r WHERE r.numeroDossier = :numeroDossier")
    int findMaxNumeroRelanceByDossier(@Param("numeroDossier") String numeroDossier);

//********************************************************************************************


    @Query(value = "SELECT TOP 1 * FROM Relances WHERE numero_dossier = :numeroDossier ORDER BY numero_relance DESC", nativeQuery = true)
    Relance findLastRelanceByDossier(@Param("numeroDossier") String numeroDossier);


//********************************************************************************************


    @Modifying
    @Query(value = "INSERT INTO relances (date_relance, numero_relance, client, commentaire, canal_relance, statut_relance, montant_action, num_echeance, numero_dossier, date_echeance, user_id, client_numero) " +
            "VALUES (:dateRelance, :numeroRelance, :client, :commentaire, :canalRelance, :statutRelance, :montantAction, :numEcheance, :numeroDossier, :dateEcheance, :userId, :numeroClient)",
            nativeQuery = true)
    void insererRelance(
            @Param("dateRelance") LocalDateTime dateRelance,
            @Param("numeroRelance") int numeroRelance,
            @Param("client") String client,
            @Param("commentaire") String commentaire,
            @Param("canalRelance") String canalRelance,
            @Param("statutRelance") String statutRelance,
            @Param("montantAction") double montantAction,
            @Param("numEcheance") int numEcheance,
            @Param("numeroDossier") String numeroDossier,
            @Param("dateEcheance") LocalDate dateEcheance,
            @Param("userId") Long userId,
            @Param("numeroClient") String numeroClient // Client's numeroClient inserted
    );





//********************************************************************************************

    @Query(value = "SELECT r.* FROM Relances r " +
            "INNER JOIN ( " +
            "  SELECT numero_dossier, num_echeance, MAX(numero_relance) AS max_num " +
            "  FROM Relances " +
            "  GROUP BY numero_dossier, num_echeance " +
            ") last ON r.numero_dossier = last.numero_dossier " +
            "       AND r.num_echeance = last.num_echeance " +
            "       AND r.numero_relance = last.max_num",
            nativeQuery = true)

    List<Relance> findLastRelanceGroupedByDossier();

//********************************************************************************************

  //  List<NouvelleEcheance> findByDossierAndRelance(String numeroDossier, int numeroRelance);

    @Query("SELECT COALESCE(MAX(r.numeroRelance), 0) FROM Relance r WHERE r.numeroDossier = :numeroDossier")
    int findMaxRelanceByNumeroDossier(@Param("numeroDossier") String numeroDossier);

//********************************************************************************************

    @Query("SELECT new com.example.demo.DTO.RelanceParMoisDTO(MONTH(r.dateRelance), COUNT(r)) " +
            "FROM Relance r GROUP BY MONTH(r.dateRelance) ORDER BY MONTH(r.dateRelance)")
    List<RelanceParMoisDTO> getRelancesParMois();
//********************************************************************************************






}





