package com.example.demo.controller;

import com.example.demo.model.UtilisateurDetails;
import com.example.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
 // ⚠️ Autorise les requêtes depuis React

public class FileUploadController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload-excel")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun fichier sélectionné.");
        }

        try {
            excelService.processExcelFile(file);
            return ResponseEntity.ok("Fichier traité et inséré en base de données.");
        } catch (Exception e) {
            e.printStackTrace();  // 👈 Pour afficher la vraie erreur dans la console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement du fichier.");
        }

    }


    // ✅ Nouveau endpoint pour importer le fichier SRG
    @PostMapping("/upload-srg")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> uploadSrgFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun fichier sélectionné.");
        }

        try {
            excelService.processSRGFile(file);
            return ResponseEntity.ok("Fichier SRG traité et inséré en base de données.");
        } catch (Exception e) {
            e.printStackTrace();  // <-- Important pour voir le vrai problème
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement du fichier SRG.");
        }
    }

    @PostMapping("/upload-operations")
    public ResponseEntity<String> uploadOperations(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Le fichier est vide !");
            }

            // Appel du service
            excelService.importFromExcel(file);

            return ResponseEntity.ok("✅ Importation des opérations réussie !");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("❌ Erreur : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Erreur inattendue : " + e.getMessage());
        }
    }



}
