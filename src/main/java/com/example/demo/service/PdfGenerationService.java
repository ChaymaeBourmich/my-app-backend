package com.example.demo.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.apache.poi.poifs.macros.Module.ModuleType.Document;

@Service
public class PdfGenerationService {

    public byte[] generatePaiementPdf(String dossier, String montantEncaisse, String client, String numeroRelance) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Création du PDF
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        // Ajout de contenu au PDF
        document.add(new Paragraph("Fiche de Paiement", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD)));
        document.add(new Paragraph("Client: " + client));
        document.add(new Paragraph("Dossier: " + dossier));
        document.add(new Paragraph("Relance n°: " + numeroRelance));
        document.add(new Paragraph("Montant encaissé: " + montantEncaisse + " MAD"));

        // Fermeture du document
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
