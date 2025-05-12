package com.example.demo.DTO;

import java.time.LocalDate;

public interface RecouvrementProjection {
    String getId();
    String getAdresse();
    LocalDate getDateAffectation();
    String getDesignation();
    String getDossier();
    LocalDate getDateFin();
    String getEcheance();
    Integer getDelai();
    String getClient();
    String getAncienNumeroProduit();
    String getDesignationProjet();
    Double getMontant();
    Double getPrixCession();
    String getProjet();
    String getTel();
    String getNumeroClient();
}
