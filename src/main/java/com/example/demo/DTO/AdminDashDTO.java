package com.example.demo.DTO;

public class AdminDashDTO {
    private String agence;
    private String commercial;
    private double montantARecouvrer;
    private double montantRecouvert;
    private double montantRestant;
    private double tauxRecouvrement;

    public AdminDashDTO(String agence, String commercial, double montantARecouvrer, double montantRecouvert, double montantRestant, double tauxRecouvrement) {
        this.agence = agence;
        this.commercial = commercial;
        this.montantARecouvrer = montantARecouvrer;
        this.montantRecouvert = montantRecouvert;
        this.montantRestant = montantRestant;
        this.tauxRecouvrement = tauxRecouvrement;
    }

    public AdminDashDTO() {
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getCommercial() {
        return commercial;
    }

    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }

    public double getMontantARecouvrer() {
        return montantARecouvrer;
    }

    public void setMontantARecouvrer(double montantARecouvrer) {
        this.montantARecouvrer = montantARecouvrer;
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
