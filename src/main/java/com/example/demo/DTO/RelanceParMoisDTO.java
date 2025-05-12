package com.example.demo.DTO;

public class RelanceParMoisDTO {
    private Integer mois;
    private Long nombreRelances;

    public RelanceParMoisDTO(Integer mois, Long nombreRelances) {
        this.mois = mois;
        this.nombreRelances = nombreRelances;
    }

    // Getters et setters
    public Integer getMois() { return mois; }
    public void setMois(Integer mois) { this.mois = mois; }

    public Long getNombreRelances() { return nombreRelances; }
    public void setNombreRelances(Long nombreRelances) { this.nombreRelances = nombreRelances; }
}
