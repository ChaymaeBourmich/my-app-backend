package com.example.demo.service;

import com.example.demo.DTO.RelanceAvecCINProjection;
import com.example.demo.model.AppelDeFond;
import com.example.demo.model.Client;
import com.example.demo.model.Relance;
import com.example.demo.repository.AppelDeFondRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.EchelonnementRepository;
import com.example.demo.repository.RelanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class RelanceService {

    @Autowired
    private RelanceRepository relanceRepository;

    @Autowired
    private AppelDeFondRepository appelDeFondRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EchelonnementRepository nouvelleEcheanceRepository;  // Injection du repository pour NouvelleEcheance

    public RelanceService(RelanceRepository relanceRepository) {
        this.relanceRepository = relanceRepository;
    }

    //******************************************************************************8
    public List<Relance> getAllRelances() {
        return relanceRepository.findAll();
    }


    //******************************************************************************8

    public List<RelanceAvecCINProjection> getDernieresRelancesAvecCinPourUser(Long userId) {
        return relanceRepository.findLastRelanceByDossierForUserWithCIN(userId);
    }

    //******************************************************************************8



    public List<Relance> getRelancesPourUser(Long userId) {
        return relanceRepository.findRelancesByUserId(userId);
    }

    @Transactional
    public Relance ajouterRelance(Relance relance, Long userId) {
        int lastNumeroRelance = relanceRepository.findMaxNumeroRelanceByDossier(relance.getNumeroDossier());
        int nextNumeroRelance = lastNumeroRelance + 1;

        LocalDateTime dateRelanceLocalDateTime = relance.getDateRelance().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        LocalDate dateEcheanceLocalDate = null;
        if (relance.getDateEcheance() != null) {
            dateEcheanceLocalDate = relance.getDateEcheance().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        if (relance.getNumClient() == null || relance.getNumClient().getNumeroClient() == null) {
            throw new IllegalArgumentException("❌ NumClient est null dans l'objet Relance.");
        }

        String numeroClientStr = relance.getNumClient().getNumeroClient();

        // 🔥 Correction ici : Optional -> Client
        Optional<Client> clientOptional = clientRepository.findByNumeroClient(numeroClientStr);
        Client clientEntity = clientOptional.orElseThrow(() ->
                new RuntimeException("❌ Client introuvable avec le numéro client : " + numeroClientStr)
        );

        relance.setNumeroRelance(nextNumeroRelance);

        relanceRepository.insererRelance(
                dateRelanceLocalDateTime,
                nextNumeroRelance,
                relance.getClient(),
                relance.getCommentaire(),
                relance.getCanalRelance(),
                relance.getStatutRelance(),
                relance.getMontantAction(),
                relance.getNumEcheance(),
                relance.getNumeroDossier(),
                dateEcheanceLocalDate,
                userId,
                clientEntity.getNumeroClient()
        );

        relance.setNumClient(clientEntity);

        return relance;
    }





    public String genererSuffixePourNouvelleEcheance(String numeroDossier) {
        // Récupérer toutes les échéances déjà générées avec ce dossier (id LIKE '%_numeroDossier')
        List<AppelDeFond> existantes = appelDeFondRepository
                .findByIdEndingWith("_" + numeroDossier);

        // Filtrer uniquement les ID commençant par "4_" pour ne prendre que les nouvelles échéances
        long count = existantes.stream()
                .filter(e -> e.getId() != null && e.getId().startsWith("4_"))
                .count();

        // Convertir 0 → A, 1 → B, etc.
        char lettre = (char) ('A' + count);

        // Numéro d'échéance fixe ou basé sur relance.getNumEcheance() si besoin
        String numeroEcheance = "3"; // à adapter selon ton besoin

        return numeroEcheance + lettre;
    }



   // public String genererIdentifiantEcheance(String dossier, int relance, int annee) {
        // Compte les échéances existantes pour ce couple
     //   List<NouvelleEcheance> echeances = relanceRepository.findByDossierAndRelance(dossier, relance);
      //  char suffixe = (char) ('A' + echeances.size());
      //  return relance + "_" + annee + "_3" + suffixe + "_" + dossier;
  //  }




}
