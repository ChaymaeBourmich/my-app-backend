package com.example.demo.DTO;

public class AdminTotauxDTO {
    private double montantTotal;
    private double montantRecouvert;
    private double montantRestant;
    private double tauxRecouvrement;

    // constructeur + getters + setters

    public AdminTotauxDTO(double montantTotal, double montantRecouvert, double tauxRecouvrement, double montantRestant) {
        this.montantTotal = montantTotal;
        this.montantRecouvert = montantRecouvert;
        this.tauxRecouvrement = tauxRecouvrement;
        this.montantRestant = montantRestant;
    }

    public AdminTotauxDTO() {
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public double getMontantRecouvert() {
        return montantRecouvert;
    }

    public void setMontantRecouvert(double montantRecouvert) {
        this.montantRecouvert = montantRecouvert;
    }

    public double getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public double getTauxRecouvrement() {
        return tauxRecouvrement;
    }

    public void setTauxRecouvrement(double tauxRecouvrement) {
        this.tauxRecouvrement = tauxRecouvrement;
    }
}
