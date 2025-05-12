package com.example.demo.DTO;

public interface RelanceAvecCINProjection {
    String getId();
    String getNumeroDossier();
    Integer getNumeroRelance();
    String getClient();
    String getCanalRelance();
    String getStatutRelance();
    String getCommentaire();
    Double getMontantAction();
    Integer getNumEcheance();
    java.util.Date getDateRelance();
    java.util.Date getDateEcheance();
    String getCin();  // ← récupéré depuis SRG_DB
}
