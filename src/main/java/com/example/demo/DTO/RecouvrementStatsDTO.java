package com.example.demo.DTO;

public class RecouvrementStatsDTO {
    private double total;
    private double recouvert;
    private double reste;
    private double taux;

    public RecouvrementStatsDTO(double total, double recouvert, double reste, double taux) {
        this.total = total;
        this.recouvert = recouvert;
        this.reste = reste;
        this.taux = taux;
    }

    public double getTotal() {
        return total;
    }

    public double getRecouvert() {
        return recouvert;
    }

    public double getReste() {
        return reste;
    }

    public double getTaux() {
        return taux;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setRecouvert(double recouvert) {
        this.recouvert = recouvert;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
