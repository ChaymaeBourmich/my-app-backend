package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Echelonnement")
public class Echelonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_dossier", nullable = false)
    private String numeroDossier;

    @Column(name = "date_relance", nullable = false)
    private LocalDate dateRelance;

    @Column(name = "numero_relance", nullable = false)
    private Integer numeroRelance;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "canal_relance")
    private String canalRelance;

    @Column(name = "statut_relance")
    private String statutRelance;

    @Column(name = "montant_action", nullable = false, precision = 18, scale = 2)
    private BigDecimal montantAction;

    @Column(name = "num_echeance", nullable = false)
    private Integer numEcheance;

    @Column(name = "numero_client")
    private String numeroClient;

    @Column(name = "date_echeance")
    private LocalDate dateEcheance;

    // Partie payée
    @Column(name = "montant_paye", precision = 18, scale = 2)
    private BigDecimal montantPaye;

    @Column(name = "date_paye")
    private LocalDate datePaye;

    @Column(name = "av_paye")
    private String avPaye;

    // Partie restante
    @Column(name = "montant_restant", precision = 18, scale = 2)
    private BigDecimal montantRestant;

    @Column(name = "date_restant")
    private LocalDate dateRestant;

    @Column(name = "av_restant")
    private String avRestant;


    @ManyToOne
    @JoinColumn(name = "relance_id", referencedColumnName = "id") // <- relation vers la relance
    @JsonBackReference
    private Relance relance;
    // Getters et Setters (tu peux les générer automatiquement dans ton IDE)


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public LocalDate getDateRelance() {
        return dateRelance;
    }

    public void setDateRelance(LocalDate dateRelance) {
        this.dateRelance = dateRelance;
    }

    public Integer getNumeroRelance() {
        return numeroRelance;
    }

    public void setNumeroRelance(Integer numeroRelance) {
        this.numeroRelance = numeroRelance;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCanalRelance() {
        return canalRelance;
    }

    public void setCanalRelance(String canalRelance) {
        this.canalRelance = canalRelance;
    }

    public String getStatutRelance() {
        return statutRelance;
    }

    public void setStatutRelance(String statutRelance) {
        this.statutRelance = statutRelance;
    }

    public BigDecimal getMontantAction() {
        return montantAction;
    }

    public void setMontantAction(BigDecimal montantAction) {
        this.montantAction = montantAction;
    }

    public Integer getNumEcheance() {
        return numEcheance;
    }

    public void setNumEcheance(Integer numEcheance) {
        this.numEcheance = numEcheance;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public LocalDate getDatePaye() {
        return datePaye;
    }

    public void setDatePaye(LocalDate datePaye) {
        this.datePaye = datePaye;
    }

    public String getAvPaye() {
        return avPaye;
    }

    public void setAvPaye(String avPaye) {
        this.avPaye = avPaye;
    }

    public BigDecimal getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(BigDecimal montantRestant) {
        this.montantRestant = montantRestant;
    }

    public LocalDate getDateRestant() {
        return dateRestant;
    }

    public void setDateRestant(LocalDate dateRestant) {
        this.dateRestant = dateRestant;
    }

    public String getAvRestant() {
        return avRestant;
    }

    public void setAvRestant(String avRestant) {
        this.avRestant = avRestant;
    }

    public Relance getRelance() {
        return relance;
    }

    public void setRelance(Relance relance) {
        this.relance = relance;
    }
}
