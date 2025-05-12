package com.example.demo.DTO;

import java.time.LocalDate;

public class RecouvrementDTO {
    private String id;
    private String adresse;
    private LocalDate dateAffectation;
    private String designation;
    private String dossier;
    private LocalDate dateFin;
    private String echeance;
    private Integer delai;
    private String client;
    private String ancienNumeroProduit;
    private String designationProjet;
    private Double montant;
    private Double prixCession;
    private String projet;
    private String tel;
    private String numeroClient;

    // ✅ Constructeur avec tous les champs (dans le même ordre que SELECT)
    public RecouvrementDTO(String id, String adresse, LocalDate dateAffectation, String designation, String dossier,
                           LocalDate dateFin, String echeance, Integer delai, String client, String ancienNumeroProduit,
                           String designationProjet, Double montant, Double prixCession, String projet, String tel, String numeroClient) {
        this.id = id;
        this.adresse = adresse;
        this.dateAffectation = dateAffectation;
        this.designation = designation;
        this.dossier = dossier;
        this.dateFin = dateFin;
        this.echeance = echeance;
        this.delai = delai;
        this.client = client;
        this.ancienNumeroProduit = ancienNumeroProduit;
        this.designationProjet = designationProjet;
        this.montant = montant;
        this.prixCession = prixCession;
        this.projet = projet;
        this.tel = tel;
        this.numeroClient = numeroClient;
    }

    // ✅ Getters (obligatoires pour que Spring puisse les utiliser)
    public String getId() { return id; }
    public String getAdresse() { return adresse; }
    public LocalDate getDateAffectation() { return dateAffectation; }
    public String getDesignation() { return designation; }
    public String getDossier() { return dossier; }
    public LocalDate getDateFin() { return dateFin; }
    public String getEcheance() { return echeance; }
    public Integer getDelai() { return delai; }
    public String getClient() { return client; }
    public String getAncienNumeroProduit() { return ancienNumeroProduit; }
    public String getDesignationProjet() { return designationProjet; }
    public Double getMontant() { return montant; }
    public Double getPrixCession() { return prixCession; }
    public String getProjet() { return projet; }
    public String getTel() { return tel; }
    public String getNumeroClient() { return numeroClient; }
}
