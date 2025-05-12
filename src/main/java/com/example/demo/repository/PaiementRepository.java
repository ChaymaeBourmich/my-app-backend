package com.example.demo.repository;

import com.example.demo.model.Paiement;
import com.example.demo.model.User;
import com.example.demo.model.UtilisateurDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    @Query("SELECT p FROM Paiement p WHERE p.user.id = :userId")
    List<Paiement> findByUserId(@Param("userId") Long userId);
    List<Paiement> findByUser(User user);

    @Query("SELECT SUM(p.montantEncaisse) FROM Paiement p WHERE p.user = :user")
    Double sumMontantByUser(@Param("user") User user);

    @Query("SELECT SUM(p.montantEncaisse) FROM Paiement p")
    Double sumMontantGlobal();



}
