package com.example.demo.DTO;

import com.example.demo.model.Operation;
import com.example.demo.model.Role;

import java.util.List;
import java.util.Set;

public class UtilisateurRequest {
    private Long utilisateurId;
    private String username;
    private String password;
    private Role roles;
    private String type;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String agence;
    List<OperationRequest> operations;
    private boolean premiereConnexion;

    public UtilisateurRequest() {
    }

    public UtilisateurRequest(Long utilisateurId, String username, String password, String role, String type, String nom, String prenom, String email, String telephone, String agence, List<OperationRequest> operations) {
        this.utilisateurId = utilisateurId;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.type = type;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.agence = agence;
        this.operations = operations;
    }

    // Getters et setters
    public Long getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(Long utilisateurId) { this.utilisateurId = utilisateurId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }


    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getAgence() { return agence; }
    public void setAgence(String agence) { this.agence = agence; }

    public List<OperationRequest> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationRequest> operations) {
        this.operations = operations;
    }

    public Role getRole() {
        return roles;
    }

    public void setRole(Role roles) {
        this.roles = roles;
    }

    public boolean isPremiereConnexion() {
        return premiereConnexion;
    }

    public void setPremiereConnexion(boolean premiereConnexion) {
        this.premiereConnexion = premiereConnexion;
    }

    @Override
    public String toString() {
        return "UtilisateurRequest{" +
                "utilisateurId=" + utilisateurId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + roles + '\'' +
                ", type='" + type + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", agence='" + agence + '\'' +
                ", operations=" + operations +
                '}';
    }
}

