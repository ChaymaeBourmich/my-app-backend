package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "Appel_Fond_DB")
public class Recouvrement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;



    @Column(name = "Désignation de la division")
    private String designation;

    @Column(name = "Désignation du projet")
    private String designationProjet;

    @Column(name = "Projet")
    private String projet;


    @Column(name = "Montant")
    private Double prix;

    @Column(name = "N° Dossier")
    private String dossier;

    @Column(name = "Date affectation")
    private LocalDate dateaffectation;

    @Column(name = "Date fin")
    private LocalDate dateecheance;


    @Column(name = "Echéance")
    private String echéance;

    @Column(name = "Prix de Cession")
    private Double prix_cession;



    @Column(name = "Ancien numéro de produit")
    private String numproduit;

    @Column(name = "Client")
    private String nom;


    @Column(name = "Adresse client")
    private String adresse;

    @Column(name = "N° Téléphone")
    private String tel;


    @Column(name = "Delai")
    private Integer joursRestants;


    @Transient
    private String numeroClient;



    public String getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }



    public Recouvrement() {
    }







    // Getters et Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    public LocalDate getDateaffectation() {
        return dateaffectation;
    }

    public void setDateaffectation(LocalDate dateaffectation) {
        this.dateaffectation = dateaffectation;
    }

    public LocalDate getDateecheance() {
        return dateecheance;
    }

    public void setDateecheance(LocalDate dateecheance) {
        this.dateecheance = dateecheance;
    }

    public String getEchéance() {
        return echéance;
    }

    public void setEchéance(String echéance) {
        this.echéance = echéance;
    }

    public Double getPrix_cession() {
        return prix_cession;
    }

    public void setPrix_cession(Double prix_cession) {
        this.prix_cession = prix_cession;
    }



    public String getNumproduit() {
        return numproduit;
    }

    public void setNumproduit(String numproduit) {
        this.numproduit = numproduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getJoursRestants() {
        return joursRestants;
    }

    public void setJoursRestants(Integer joursRestants) {
        this.joursRestants = joursRestants;
    }

    public String getDesignationProjet() {
        return designationProjet;
    }

    public void setDesignationProjet(String designationProjet) {
        this.designationProjet = designationProjet;
    }

    @Override
    public String toString() {
        return "Recouvrement{" +
                "id='" + id + '\'' +
                ", designation='" + designation + '\'' +
                ", designationProjet='" + designationProjet + '\'' +
                ", projet='" + projet + '\'' +
                ", prix=" + prix +
                ", dossier='" + dossier + '\'' +
                ", dateaffectation=" + dateaffectation +
                ", dateecheance=" + dateecheance +
                ", echéance='" + echéance + '\'' +
                ", prix_cession=" + prix_cession +
                ", numproduit='" + numproduit + '\'' +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", joursRestants=" + joursRestants +
                ", numeroClient='" + numeroClient + '\'' +
                '}';
    }
}
