package com.example.demo.service;

import com.example.demo.DTO.RecouvrementDTO;
import com.example.demo.DTO.RecouvrementProjection;
import com.example.demo.model.AppelDeFond;
import com.example.demo.model.Recouvrement;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RecouvrementServiceImpl implements RecouvrementService {
    @Autowired
    private RelanceRepository relanceRepository;

    @Autowired
    private RecouvrementRepository recouvrementRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private AppelDeFondRepository appelFondRepository;



    public List<Recouvrement> getRecouvrementPourUser(Long userId) {
        List<Object[]> rows = recouvrementRepository.findRecouvrementsByUserId(userId);
        List<Recouvrement> result = new ArrayList<>();

        for (Object[] row : rows) {
            Recouvrement r = new Recouvrement();
            r.setId(toString(row[0]));
            r.setAdresse(toString(row[1]));
            r.setDateaffectation(toLocalDate(row[2]));
            r.setDesignation(toString(row[3]));
            r.setDossier(toString(row[4]));
            r.setDateecheance(toLocalDate(row[5]));
            r.setEchéance(toString(row[6]));
            r.setJoursRestants(toInteger(row[7]));
            r.setNom(toString(row[8]));
            r.setNumproduit(toString(row[9]));
            r.setDesignationProjet(toString(row[10]));
            r.setPrix(toDouble(row[11]));
            r.setPrix_cession(toDouble(row[12]));
            r.setProjet(toString(row[13]));
            r.setTel(toString(row[14]));
            r.setNumeroClient(toString(row[15])); // ✅ fonctionne si champ bien sélectionné

            result.add(r);
        }

        return result;
    }








    public List<Recouvrement> getAllRecouvrementData() {

        List<Object[]> rows = recouvrementRepository.findRecouvrementsWithNumeroClient();
        List<Recouvrement> result = new ArrayList<>();

        for (Object[] row : rows) {
            Recouvrement r = new Recouvrement();
            r.setId(toString(row[0]));
            r.setAdresse(toString(row[1]));
            r.setDateaffectation(toLocalDate(row[2]));
            r.setDesignation(toString(row[3]));
            r.setDossier(toString(row[4]));
            r.setDateecheance(toLocalDate(row[5]));
            r.setEchéance(toString(row[6]));
            r.setJoursRestants(toInteger(row[7]));
            r.setNom(toString(row[8]));
            r.setNumproduit(toString(row[9]));
            r.setDesignationProjet(toString(row[10]));
            r.setPrix(toDouble(row[11]));
            r.setPrix_cession(toDouble(row[12]));
            r.setProjet(toString(row[13]));
            r.setTel(toString(row[14]));
            r.setNumeroClient(toString(row[15])); // ✅ FONCTIONNE MAINTENANT

            // DEBUG LOG

            result.add(r);

        }


        return result;
    }

    // Fonctions utilitaires propres
    private String toString(Object obj) {
        return obj != null ? obj.toString() : null;
    }

    private Double toDouble(Object obj) {
        return obj != null ? Double.parseDouble(obj.toString()) : null;
    }

    private Integer toInteger(Object obj) {
        return obj != null ? Integer.parseInt(obj.toString()) : null;
    }

    private LocalDate toLocalDate(Object obj) {
        if (obj instanceof java.sql.Date) {
            java.sql.Date date = (java.sql.Date) obj;
            return date.toLocalDate();
        }
        return null;
    }




    @Override
    public List<Recouvrement> getDossiersEnEtatJuridique() {
        List<String> dossierNumbers = relanceRepository.findDossierNumbersWithMoreThan3RelancesAndTF();
        if (dossierNumbers == null || dossierNumbers.isEmpty()) {
            return Collections.emptyList();
        }
        return recouvrementRepository.findByDossierIn(dossierNumbers);
    }


}
