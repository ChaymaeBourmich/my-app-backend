package com.example.demo.service;

import com.example.demo.model.Paiement;
import com.example.demo.model.User;
import com.example.demo.repository.PaiementRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PaiementService {


    @Autowired
    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    // Enregistrer un paiement
    public Paiement addPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }
    @Autowired
    private PaiementRepository paiementRepository; // Injection du repository
    public Paiement ajouterPaiement(Paiement paiement, User user) {
        paiement.setUser(user); // association de l'utilisateur connecté
        return paiementRepository.save(paiement);
    }

    public void generatePDF(Paiement paiement) throws DocumentException, IOException {
        // Récupérer le chemin du répertoire de téléchargement
        String userHome = System.getProperty("user.home");
        String downloadDir = userHome + "/Downloads";
        String outputPath = downloadDir + "/paiement_" + paiement.getNumeroRelance() + ".pdf";

        // Créer un document PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));

        // Ouvrir le document pour y ajouter du contenu
        document.open();

        // Ajouter des informations à propos du paiement
        document.add(new Paragraph("Détails du paiement :"));
        document.add(new Paragraph("Client : " + paiement.getClient()));
        document.add(new Paragraph("Dossier : " + paiement.getNumDossier()));
        document.add(new Paragraph("Montant : " + paiement.getMontantEncaisse()));
        document.add(new Paragraph("Mode de Paiement : " + paiement.getModePaiement()));
        document.add(new Paragraph("Statut : " + paiement.getStatutPaiement()));

        // Fermer le document pour finaliser le fichier PDF
        document.close();

        // Enregistrer le paiement dans la base de données
        paiementRepository.save(paiement);

        System.out.println("PDF généré avec succès dans le dossier Téléchargements : " + outputPath);
    }
}
