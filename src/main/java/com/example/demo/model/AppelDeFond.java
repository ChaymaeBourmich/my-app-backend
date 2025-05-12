package com.example.demo.model;



import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "appel_fond_db")
public class AppelDeFond {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "[N° Dossier]") // Utiliser les crochets pour les colonnes avec des espaces ou des caractères spéciaux
    private String numeroDossier;

    @Column(name = "Organisation commerciale")
    private String organisationCommerciale;

    @Column(name = "Division")
    private String division;

    @Column(name = "Désignation de la division")
    private String designationDivision;

    @Column(name = "Projet")
    private String projet;

    @Column(name = "Client")
    private String client;

    @Column(name = "Prix de Cession")
    private Double prixCession;

    @Column(name = "Date livraison")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLivraison;



    @Column(name = "Désignation du projet")
    private String designationProjet;

    @Column(name = "Adresse client")
    private String adresseClient;

    @Column(name = "N° Téléphone")
    private String numeroTelephone;

    @Column(name = "Ancien numéro de produit")
    private String ancienNumeroProduit;

    @Column(name = "Nouveau numéro de produit")
    private String nouveauNumeroProduit;

    @Column(name = "Date affectation")
    @Temporal(TemporalType.DATE)
    private Date dateAffectation;

    @Column(name = "Total des Avances")
    private Double totalDesAvances;

    @Column(name = "Reste à payer")
    private Double resteAPayer;

    @Column(name = "Echéance")
    private int numEcheance;

    @Column(name = "Date fin")
    @Temporal(TemporalType.DATE)
    private LocalDate dateFin;

    @Column(name = "Montant")
    private Double montant;

    @Column(name = "Ecart calendrier")
    private Double ecartCalendrier;

    @Column(name = "delai")
    private Integer delai;


    // Add other fields accordingly

    // Getters and Setters

    public String getOrganisationCommerciale() {
        return organisationCommerciale;
    }

    public void setOrganisationCommerciale(String organisationCommerciale) {
        this.organisationCommerciale = organisationCommerciale;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDesignationDivision() {
        return designationDivision;
    }

    public void setDesignationDivision(String designationDivision) {
        this.designationDivision = designationDivision;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Double getPrixCession() {
        return prixCession;
    }

    public void setPrixCession(Double prixCession) {
        this.prixCession = prixCession;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignationProjet() {
        return designationProjet;
    }

    public void setDesignationProjet(String designationProjet) {
        this.designationProjet = designationProjet;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAncienNumeroProduit() {
        return ancienNumeroProduit;
    }

    public void setAncienNumeroProduit(String ancienNumeroProduit) {
        this.ancienNumeroProduit = ancienNumeroProduit;
    }

    public String getNouveauNumeroProduit() {
        return nouveauNumeroProduit;
    }

    public void setNouveauNumeroProduit(String nouveauNumeroProduit) {
        this.nouveauNumeroProduit = nouveauNumeroProduit;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Double getTotalDesAvances() {
        return totalDesAvances;
    }

    public void setTotalDesAvances(Double totalDesAvances) {
        this.totalDesAvances = totalDesAvances;
    }

    public Double getResteAPayer() {
        return resteAPayer;
    }

    public void setResteAPayer(Double resteAPayer) {
        this.resteAPayer = resteAPayer;
    }

    public int getEcheance() {
        return numEcheance;
    }

    public void setEcheance(int echeance) {
        this.numEcheance = echeance;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getEcartCalendrier() {
        return ecartCalendrier;
    }

    public void setEcartCalendrier(Double ecartCalendrier) {
        this.ecartCalendrier = ecartCalendrier;
    }

    public Integer getDelai() {
        return delai;
    }

    public void setDelai(Integer delai) {
        this.delai = delai;
    }


}
