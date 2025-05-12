package com.example.demo.service;

import com.example.demo.model.Echelonnement;
import com.example.demo.model.Relance;
import com.example.demo.repository.EchelonnementRepository;
import com.example.demo.repository.RelanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchelonnementService {

    @Autowired
    private EchelonnementRepository echelonnementRepository;

    // Méthode pour ajouter un nouvel échelonnement
    @Autowired
    private RelanceRepository relanceRepository;


    @Autowired
    public EchelonnementService(EchelonnementRepository echelonnementRepository) {
        this.echelonnementRepository = echelonnementRepository;
    }

    @Transactional
    public Echelonnement saveEchelonnement(Echelonnement echelonnement, String relanceId, String numeroClient) {

        if (relanceId != null) {
            Relance relance = relanceRepository.findById(relanceId)
                    .orElseThrow(() -> new IllegalArgumentException("Relance non trouvée avec id: " + relanceId));
            echelonnement.setRelance(relance);
        }

        if (numeroClient != null) {
            echelonnement.setNumeroClient(numeroClient);
        }

        return echelonnementRepository.save(echelonnement);
    }


}
