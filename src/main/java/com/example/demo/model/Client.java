package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "numero_client", unique = true, nullable = false)
    private String numeroClient;

    @Column(name = "CIN")
    private String cin;

    @Column(name = "nom_prenom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "CIN2")
    private String cin2;

    @Column(name = "nom_prenom2")
    private String nom_prenom2;

    @Column(name = "adresse2")
    private String adresse2;

    @Column(name = "telephone2")
    private String telephone2;

    @Column(name = "email2")
    private String email2;


    @OneToMany(mappedBy = "client")
    private List<Relance> relances = new ArrayList<>();



    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin2() {
        return cin2;
    }

    public void setCin2(String cin2) {
        this.cin2 = cin2;
    }

    public String getNom_prenom2() {
        return nom_prenom2;
    }

    public void setNom_prenom2(String nom_prenom2) {
        this.nom_prenom2 = nom_prenom2;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

    public List<Relance> getRelances() {
        return relances;
    }

    public void setRelances(List<Relance> relances) {
        this.relances = relances;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numeroClient='" + numeroClient + '\'' +
                ", cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", cin2='" + cin2 + '\'' +
                ", nom_prenom2='" + nom_prenom2 + '\'' +
                ", adresse2='" + adresse2 + '\'' +
                ", telephone2='" + telephone2 + '\'' +
                ", email2='" + email2 + '\'' +
                '}';
    }
}
