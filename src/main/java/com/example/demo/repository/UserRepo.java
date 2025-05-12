package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UtilisateurDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.utilisateurDetails d WHERE d.id = :utilisateurId")
    Optional<User> findByUtilisateurDetailsId(@Param("utilisateurId") Long utilisateurId);

    @Query("SELECT u FROM User u WHERE u.username = :email AND u.id <> :id")
    Optional<User> findByUsernameExcludingId(@Param("email") String email, @Param("id") Long id);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r = :role")
    List<User> findAllByRole(@Param("role") Role role);

}