package com.example.demo.controller;

import com.example.demo.DTO.RelanceAvecCINProjection;
import com.example.demo.DTO.RelanceParMoisDTO;
import com.example.demo.model.Recouvrement;
import com.example.demo.model.Relance;
import com.example.demo.model.User;
import com.example.demo.repository.PaiementRepository;
import com.example.demo.repository.RecouvrementRepository;
import com.example.demo.repository.RelanceRepository;
import com.example.demo.repository.SRGRepository;
import com.example.demo.service.RelanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/relances")
@CrossOrigin(origins = "https://my-app-frontend-beta.vercel.app")
// Autorise le frontend React

public class RelanceController {

    private final RelanceRepository relanceRepository;
    public RelanceController(RelanceRepository relanceRepository) {
        this.relanceRepository = relanceRepository;
    }

    @Autowired
    private RelanceService relanceService;
    @Autowired
    private RecouvrementRepository recouvrementRepository;

    @Autowired
    private SRGRepository srgRepository;

    @PostMapping("/add")
    public ResponseEntity<Relance> ajouterRelance(
            @RequestBody Relance relance,
            @AuthenticationPrincipal User user
    ) {
        try {
            Relance savedRelance = relanceService.ajouterRelance(relance, user.getId());
            return ResponseEntity.ok(savedRelance);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }




    @PutMapping
    public ResponseEntity<Relance> updateRelance(@RequestBody Relance updatedRelance) {
        // Comme tu vas recevoir l'objet complet avec l'id composé, pas besoin de @PathVariable
        Relance savedRelance = relanceRepository.save(updatedRelance);
        return ResponseEntity.ok(savedRelance);
    }
    //******************************************************************************8
    // 🔐 Accessible uniquement aux commerciaux
    @GetMapping("/by-user")
    @PreAuthorize("hasRole('COMMERCIAL')")
    public ResponseEntity<List<Relance>> getRelancesPourUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(relanceService.getRelancesPourUser(user.getId()));
    }

    //******************************************************************************8

    @GetMapping("/all")
    public ResponseEntity<List<Relance>> getAllRelances() {
        return ResponseEntity.ok(relanceService.getAllRelances());
    }

    //******************************************************************************8


    @GetMapping("/latest/{numeroDossier}")
    public Relance getLastRelanceByDossier(@PathVariable String numeroDossier) {
        return relanceRepository.findLastRelanceByDossier(numeroDossier);
    }


    @GetMapping("/etat-juridique")
    public List<String> getDossiersEnEtatJuridique() {
        List<String> dossiers = relanceRepository.findDossierNumbersWithAtLeastThreeRelances();
        System.out.println("📌 Dossiers avec 3+ relances: " + dossiers.size());
        return dossiers;
    }

    //***************************************************************************
    @GetMapping("/last-by-dossier")
    public ResponseEntity<List<Relance>> getLastRelanceByDossier() {
        List<Relance> latestRelances = relanceRepository.findLastRelanceGroupedByDossier();
        return ResponseEntity.ok(latestRelances);
    }

    //***************************************************************************

    @GetMapping("/last-by-dossier/by-user")
    @PreAuthorize("hasRole('COMMERCIAL')")
    public ResponseEntity<List<RelanceAvecCINProjection>> getDernieresRelancesPourUser(@AuthenticationPrincipal User user) {
        List<RelanceAvecCINProjection> relances = relanceRepository.findLastRelanceByDossierForUserWithCIN(user.getId());
        return ResponseEntity.ok(relances);
    }


    //***************************************************************************

    @GetMapping("/echeances/by-client/{numeroClient}")
    public List<Recouvrement> getEcheancesByClient(@PathVariable String numeroClient) {
        List<String> dossiers = srgRepository.findDossierByNumeroClient(numeroClient);
        System.out.println("🔍 Dossiers pour client " + numeroClient + ": " + dossiers);
        if (dossiers == null || dossiers.isEmpty()) {
            return List.of();
        }
        List<Recouvrement> echeances = recouvrementRepository.findAllByDossierIn(dossiers);
        System.out.println("✅ Échéances trouvées: " + echeances.size());
        return echeances;
    }
    //***************************************************************************

    @GetMapping("/max-relance/{numeroDossier}")
    public int getMaxRelanceByDossier(@PathVariable String numeroDossier) {
        return relanceRepository.findMaxRelanceByNumeroDossier(numeroDossier);
    }
    //***************************************************************************


    @GetMapping("/par-mois")
    public List<RelanceParMoisDTO> getRelancesParMois() {
        return relanceRepository.getRelancesParMois();
    }



}

