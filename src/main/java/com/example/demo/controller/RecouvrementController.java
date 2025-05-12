package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecouvrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000") // Permettre les requêtes depuis le frontend React
public class RecouvrementController {

    @Autowired
    private RecouvrementService recouvrementService;
    @Autowired
    private RecouvrementRepository recouvrementRepository;
    @Autowired
    private SRGRepository srgRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RelanceRepository relanceRepository;
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private UtilisateurDetailsRepository utilisateurDetailsRepository;

    // 🔹 Récupérer tous les recouvrements
    @GetMapping("/recouvrement")
    public List<Recouvrement> getAllRecouvrementData() {
        return recouvrementService.getAllRecouvrementData();
    }

    @GetMapping("/recouvrement/by-user")
    public List<Recouvrement> getDossiersPourUser(@AuthenticationPrincipal User user) {
        return recouvrementService.getRecouvrementPourUser(user.getId());
    }

    @GetMapping("/etat-juridique")
    public List<Recouvrement> getDossiersEnEtatJuridique() {
        List<Recouvrement> dossiers = recouvrementService.getDossiersEnEtatJuridique();
        System.out.println("Nombre de dossiers en état juridique: " + dossiers.size());
        return dossiers;
    }

    @GetMapping("/echeances/by-client/{numeroClient}")
    public List<Recouvrement> getEcheancesByClient(@PathVariable String numeroClient) {
        List<String> dossiers = srgRepository.findDossierByNumeroClient(numeroClient);
        if (dossiers == null || dossiers.isEmpty()) {
            return List.of(); // retour propre
        }
        return recouvrementRepository.findAllByDossierIn(dossiers);
    }

    @GetMapping("/clients/all-numeros")
    public List<String> getAllNumeroClients() {
        return srgRepository.findAllDistinctNumeroClients();
    }

    @GetMapping("/dashboard/commercial")
    public ResponseEntity<?> getReportingCommercial(Principal principal) {
        String username = principal.getName(); // récupère le commercial connecté
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // récupérer toutes les relances / paiements / échéances liés à ce user
        List<Relance> relances = relanceRepository.findByUser(user);
        List<Paiement> paiements = paiementRepository.findByUser(user);

        Map<String, Object> result = new HashMap<>();
        result.put("nbRelances", relances.size());
        result.put("totalPaiements", paiements.stream().mapToDouble(Paiement::getMontantEncaisse).sum());
        result.put("dernierPaiement", paiements.stream().max(Comparator.comparing(Paiement::getDateEncaissement)).orElse(null));

        return ResponseEntity.ok(result);
    }




}
