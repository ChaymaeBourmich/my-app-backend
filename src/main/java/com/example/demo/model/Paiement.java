package com.example.demo.model;

import com.example.demo.model.Relance;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "paiement")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cin;

    @Column(name = "num_dossier")
    private String numDossier;

    @Column(name = "montant_encaisse")
    private Double montantEncaisse;

    private String statutPaiement;

    @Column(name = "type_paiement")
    private String type_paiement;

    private String modePaiement;

    @Column(name = "date_encaissement")
    private Date dateEncaissement;

    @Column(name = "ordre_av")
    private String ordreAv;

    @OneToMany(mappedBy = "paiement", cascade = CascadeType.ALL)
    private List<Relance> relances;


    @Column(name = "client")  // Assurez-vous que cette colonne existe
    private String client;

    @Column(name = "numero_relance")  // Assurez-vous que cette colonne existe
    private String numeroRelance;

    @Column(name = "reference_cheque")
    private String referenceCheque;

    @Column(name = "banque_cheque")
    private String banqueCheque;

    @Column(name = "reference_paiement")
    private String referencePaiement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(String numDossier) {
        this.numDossier = numDossier;
    }

    public Double getMontantEncaisse() {
        return montantEncaisse;
    }

    public void setMontantEncaisse(Double montantEncaisse) {
        this.montantEncaisse = montantEncaisse;
    }

    public String getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(String statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Date getDateEncaissement() {
        return dateEncaissement;
    }

    public void setDateEncaissement(Date dateEncaissement) {
        this.dateEncaissement = dateEncaissement;
    }

    public String getOrdreAv() {
        return ordreAv;
    }

    public void setOrdreAv(String ordreAv) {
        this.ordreAv = ordreAv;
    }

    public List<Relance> getRelances() {
        return relances;
    }

    public void setRelances(List<Relance> relances) {
        this.relances = relances;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getNumeroRelance() {
        return numeroRelance;
    }

    public void setNumeroRelance(String numeroRelance) {
        this.numeroRelance = numeroRelance;
    }

    public String getReferenceCheque() {
        return referenceCheque;
    }

    public void setReferenceCheque(String referenceCheque) {
        this.referenceCheque = referenceCheque;
    }

    public String getBanqueCheque() {
        return banqueCheque;
    }

    public void setBanqueCheque(String banqueCheque) {
        this.banqueCheque = banqueCheque;
    }

    public String getReferencePaiement() {
        return referencePaiement;
    }

    public void setReferencePaiement(String referencePaiement) {
        this.referencePaiement = referencePaiement;
    }

    public String getType_paiement() {
        return type_paiement;
    }

    public void setType_paiement(String type_paiement) {
        this.type_paiement = type_paiement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Autres getters/setters...
}
