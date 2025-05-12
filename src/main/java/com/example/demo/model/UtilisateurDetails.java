package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UtilisateurDetails")
public class UtilisateurDetails {

    @Id
    @Column(name = "utilisateur_id")
    private Long id;


    @OneToOne
    @MapsId
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @Column(nullable = true)
    private String nom;

    @Column(nullable = true)
    private String prenom;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String telephone;

    @Column(nullable = true)
    private String agence;

    @Column(name = "type_utilisateur", nullable = true)
    private String type; // COMMERCIAL ou SAC

    @OneToMany(mappedBy = "utilisateurDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Operation> operations = new ArrayList<>();



    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }



}
