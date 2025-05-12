package com.example.demo.repository;

import com.example.demo.model.Operation;
import com.example.demo.model.UtilisateurDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByUtilisateurDetails(UtilisateurDetails utilisateurDetails); // 💡 méthode manquante
    boolean existsByUtilisateurDetailsAndCodeOperationAndOperation(UtilisateurDetails utilisateurDetails, String codeOperation, String operationName);


}
