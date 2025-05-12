package com.example.demo.service;

import com.example.demo.model.Paiement;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelExportService {

    public List<Paiement> importExcelFile(MultipartFile file) throws IOException {
        List<Paiement> paiements = new ArrayList<>();

        // Obtenir l'input stream du fichier
        try (InputStream inputStream = file.getInputStream()) {
            // Créer une instance du workbook pour le fichier Excel
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Récupère la première feuille

            // Itérer sur les lignes de la feuille Excel
            Iterator<Row> rowIterator = sheet.iterator();

            // Sauter la première ligne (entêtes)
            if (rowIterator.hasNext()) rowIterator.next();

            // Lire chaque ligne et ajouter les paiements à la liste
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Paiement paiement = new Paiement();

                // Lire les cellules de la ligne
                paiement.setNumDossier(getStringCellValue(row, 0)); // Cellule Numéro Dossier
                paiement.setOrdreAv(getStringCellValue(row, 1)); // Cellule Ordre AV
                paiement.setClient(getStringCellValue(row, 2)); // Cellule Client
                paiement.setMontantEncaisse(getDoubleCellValue(row, 3)); // Cellule Montant Encaisse
                paiement.setModePaiement(getStringCellValue(row, 4)); // Cellule Mode Paiement
                paiement.setStatutPaiement(getStringCellValue(row, 5)); // Cellule Statut
                paiement.setDateEncaissement(getDateCellValue(row, 6)); // Cellule Date Encaissement (conversion à Date)

                // Ajouter le paiement à la liste
                paiements.add(paiement);
            }

            workbook.close();
        }

        return paiements;
    }

    // Helper method to get String value from a cell
    private String getStringCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        return (cell != null) ? cell.getStringCellValue() : "";
    }

    // Helper method to get Double value from a cell
    private Double getDoubleCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        return (cell != null) ? cell.getNumericCellValue() : 0.0;
    }

    // Helper method to get Date value from a cell
    private Date getDateCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            // Convertir LocalDate en java.util.Date
            LocalDate localDate = cell.getDateCellValue().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return null; // Return null if the cell is not a date
    }
}
