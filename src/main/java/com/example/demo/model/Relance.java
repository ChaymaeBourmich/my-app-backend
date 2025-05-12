package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "relances")
public class Relance {


    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private String id;

    @Column(name = "numero_dossier") // Adapter selon le nom exact en SQL Server
    private String numeroDossier;

    @Column(name = "dateRelance")
    private Date dateRelance;

    @Column(name = "numeroRelance")
    private Integer numeroRelance;

    @Column(name = "client")
    private String client;



    @Column(name = "canalRelance")
    private String canalRelance;

    @Column(name = "statutRelance")
    private String statutRelance;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "montantAction")
    private double montantAction;

    @Column(name = "numEcheance")
    private int numEcheance;

    @Column(name = "date_echeance")
    private Date dateEcheance;

    @ManyToOne
    @JoinColumn(name = "paiement_id")
    private Paiement paiement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_numero", referencedColumnName = "numero_client")
    private Client NumClient;

    @OneToMany(mappedBy = "relance", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Echelonnement> echeances;


    public Relance() {
    }








    public String getId() {
        return id;
    }


    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public Date getDateRelance() {
        return dateRelance;
    }

    public void setDateRelance(Date dateRelance) {
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public double getMontantAction() {
        return montantAction;
    }

    public void setMontantAction(double montantAction) {
        this.montantAction = montantAction;
    }

    public int getNumEcheance() {
        return numEcheance;
    }

    public void setNumEcheance(int numEcheance) {
        this.numEcheance = numEcheance;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getNumClient() {
        return NumClient;
    }

    public void setNumClient(Client numClient) {
        NumClient = numClient;
    }

    public List<Echelonnement> getEcheances() {
        return echeances;
    }

    public void setEcheances(List<Echelonnement> echeances) {
        this.echeances = echeances;
    }


    @Override
    public String toString() {
        return "Relance{" +
                "id='" + id + '\'' +
                ", numeroDossier='" + numeroDossier + '\'' +
                ", dateRelance=" + dateRelance +
                ", numeroRelance=" + numeroRelance +
                ", client='" + client + '\'' +
                ", canalRelance='" + canalRelance + '\'' +
                ", statutRelance='" + statutRelance + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", montantAction=" + montantAction +
                ", numEcheance=" + numEcheance +
                ", dateEcheance=" + dateEcheance +
                ", paiement=" + paiement +
                ", user=" + user +
                ", NumClient=" + NumClient +
                ", echeances=" + echeances +
                '}';
    }
}
