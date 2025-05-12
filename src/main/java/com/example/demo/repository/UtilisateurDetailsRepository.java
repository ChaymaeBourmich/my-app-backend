package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UtilisateurDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurDetailsRepository extends JpaRepository<UtilisateurDetails, Long> {
    Optional<UtilisateurDetails> findByUser(User user);
    Optional<UtilisateurDetails> findByEmail(String email);


}
