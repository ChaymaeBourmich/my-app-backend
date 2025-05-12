package com.example.demo.controller;

import com.example.demo.model.Paiement;
import com.example.demo.model.User;
import com.example.demo.repository.PaiementRepository;
import com.example.demo.repository.RecouvrementRepository;
import com.example.demo.service.ExcelExportService;
import com.example.demo.service.PaiementService;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "http://localhost:3000") // Autorise le frontend React
public class PaiementController {

    private final PaiementService paiementService;

    @Autowired
    private  ExcelExportService excelExportService;

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    // Injecter le service PaiementService
    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    // Endpoint pour ajouter un paiement
    @PostMapping("/add")
    public ResponseEntity<String> addPaiement(@RequestBody Paiement paiement,@AuthenticationPrincipal User user) {
        try {
            paiement.setUser(user);

            // Appeler le service pour traiter le paiement et générer le PDF
            paiementService.generatePDF(paiement);

            // Retourner une réponse HTTP 200 avec un message de succès
            return ResponseEntity.ok("Paiement ajouté avec succès et PDF généré !");
        } catch (Exception e) {
            // En cas d'erreur, retourner une réponse HTTP 500 avec un message d'erreur
            return ResponseEntity.status(500).body("Erreur lors de l'ajout du paiement : " + e.getMessage());
        }
    }




    @GetMapping("/by-user")
    @PreAuthorize("hasRole('COMMERCIAL')")
    public ResponseEntity<List<Paiement>> getPaiementsPourUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(paiementRepository.findByUserId(user.getId()));
    }
    @GetMapping("/all")
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }
    // Endpoint pour importer le fichier Excel
    // Endpoint pour importer un fichier Excel




}
