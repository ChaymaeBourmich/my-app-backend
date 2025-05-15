package com.example.demo.service;

import com.example.demo.model.Operation;
import com.example.demo.model.UtilisateurDetails;
import com.example.demo.repository.OperationRepository;
import com.example.demo.repository.UtilisateurDetailsRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@Service
public class ExcelService {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UtilisateurDetailsRepository utilisateurDetailsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void processExcelFile(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // Vider la table avant d'insérer les nouvelles données
        jdbcTemplate.execute("DELETE FROM Appel_Fond_Source");

        // Lire le fichier et insérer les nouvelles données
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Ignorer l'en-tête

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String organisation = getCellValue(row.getCell(0));
            String division = getCellValue(row.getCell(1));
            String designationDivision = getCellValue(row.getCell(2));
            String projet = getCellValue(row.getCell(3));
            String designationProjet = getCellValue(row.getCell(4));
            String client = getCellValue(row.getCell(5));
            String adresseClient = getCellValue(row.getCell(6));
            String telephone = getCellValue(row.getCell(7));
            String numeroDossier = getCellValue(row.getCell(8));
            String ancienNumProduit = getCellValue(row.getCell(9));
            String nouveauNumProduit = getCellValue(row.getCell(10));
            Date dateAffectation = getDateCellValue(row.getCell(11));
            Double prixCession = getNumericCellValue(row.getCell(12));
            Double totalAvances = getNumericCellValue(row.getCell(13));
            Double resteAPayer = getNumericCellValue(row.getCell(14));
            String echeance = getCellValue(row.getCell(15));
            Date dateFin = getDateCellValue(row.getCell(16));
            Double montant = getNumericCellValue(row.getCell(17));
            Double ecartCalendrier = getNumericCellValue(row.getCell(18));
            Date dateLivraison = getDateCellValue(row.getCell(19));

            // Insertion dans la table SQL Server
            jdbcTemplate.update("INSERT INTO Appel_Fond_Source ([Organisation commerciale], [Division], [Désignation de la division], [Projet], [Désignation du projet], [Client], [Adresse client], [N° Téléphone], [N° Dossier], [Ancien numéro de produit], [Nouveau numéro de produit], [Prix de Cession], [Total des Avances], [Reste à payer], [Echéance], [Date fin], [Montant], [Ecart calendrier], [Date livraison], [Date affectation]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    organisation, division, designationDivision, projet, designationProjet, client,
                    adresseClient, telephone, numeroDossier, ancienNumProduit, nouveauNumProduit,
                    prixCession, totalAvances, resteAPayer, echeance, dateFin, montant,
                    ecartCalendrier, dateLivraison, dateAffectation
            );
        }

        workbook.close();

        // Exécuter automatiquement SSIS après l'importation
        runSSISPackage();
    }

    // ✅ Correction de la méthode pour exécuter SSIS
    public void runSSISPackage() {
        try {
            String command = "DTExec /F \"C:\\Users\\DELL\\OneDrive\\Desktop\\Al Omrane\\SSIS_Packages\\" +
                    "AppelFondPackage.dtsx";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("✅ Package SSIS exécuté avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Erreur lors de l'exécution du package SSIS !");
        }
    }



    // ✅ Correction : Méthode pour lire les cellules numériques
    private Double getNumericCellValue(Cell cell) {
        if (cell == null) return 0.0;
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
        if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return 0.0;
    }

    // ✅ Correction : Méthode pour lire les cellules de type date
    private Date getDateCellValue(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.NUMERIC || !DateUtil.isCellDateFormatted(cell)) {
            return null;
        }
        return new Date(cell.getDateCellValue().getTime());
    }


    public void processSRGFile(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // Vider la table avant d'insérer les nouvelles données
        jdbcTemplate.execute("DELETE FROM SRG_Source");

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Ignorer l’en-tête

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Lire les valeurs de chaque colonne selon l’ordre de la table
            String operation = getCellValue(row.getCell(0));
            String division = getCellValue(row.getCell(1));
            String description = getCellValue(row.getCell(2));
            String designation = getCellValue(row.getCell(3));
            String typeUnite = getCellValue(row.getCell(4));
            String typeProduit = getCellValue(row.getCell(5));
            Double nombreUnite = getNumericCellValue(row.getCell(6));
            String article = getCellValue(row.getCell(7));
            String statutAdv = getCellValue(row.getCell(8));
            String designationStatutAdv = getCellValue(row.getCell(9));
            String statutVente = getCellValue(row.getCell(10));
            String numeroLot = getCellValue(row.getCell(11));
            Double superficie = getNumericCellValue(row.getCell(12));
            Double prixUnitaire = getNumericCellValue(row.getCell(13));
            Double prixTotal = getNumericCellValue(row.getCell(14));
            String titreFoncier = getCellValue(row.getCell(15));
            String numeroClient = getCellValue(row.getCell(16));
            String client = getCellValue(row.getCell(17));
            String titreCivilite = getCellValue(row.getCell(18));
            String cinRc = getCellValue(row.getCell(19));
            String dossier = getCellValue(row.getCell(20));
            String typeDocument = getCellValue(row.getCell(21));
            String designationTypeDocument = getCellValue(row.getCell(22));
            Date creation = getDateCellValue(row.getCell(23));
            Double vers1 = getNumericCellValue(row.getCell(24));
            Double vers2 = getNumericCellValue(row.getCell(25));
            Double vers3 = getNumericCellValue(row.getCell(26));
            Double totalVersement = getNumericCellValue(row.getCell(27));
            Date dateLivraison = getDateCellValue(row.getCell(28));
            Date dateContrat = getDateCellValue(row.getCell(29));
            Double versRegularis = getNumericCellValue(row.getCell(30));
            Double tropPerçuRegle = getNumericCellValue(row.getCell(31));
            Double tropPerçuNonRegle = getNumericCellValue(row.getCell(32));
            Double resteAPayer = getNumericCellValue(row.getCell(33));
            String adresse = getCellValue(row.getCell(34));
            String telephone = getCellValue(row.getCell(35));
            Double totalAvances = getNumericCellValue(row.getCell(36));

            // Insertion dans SRG_DB
            jdbcTemplate.update("INSERT INTO SRG_Source ([Opération], [Division], [Déscription], [Désignation], [Type unité], [Type produit],[Nombre unité], [Article], [Statut ADV], [Désignation statut ADV], [Statut de vente],[N° Lot], [Superficie], [Prix unitaire], [Prix totale], [Titre foncier], [Numéro Client],[Client], [Titre de civilité], [CIN/RC], [Dossier], [Type document],[Désignation type de document], [Création], [Vers1], [Vers2], [Vers3],[Total versement], [Date livraison], [Date contrat], [Vers regularis],[Trop perçu réglé], [Trop perçu non réglé], [Reste à payer], [Adresse],[Telephone], [Total avances]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    operation, division, description, designation, typeUnite, typeProduit,
                    nombreUnite, article, statutAdv, designationStatutAdv, statutVente, numeroLot,
                    superficie, prixUnitaire, prixTotal, titreFoncier, numeroClient, client,
                    titreCivilite, cinRc, dossier, typeDocument, designationTypeDocument, creation,
                    vers1, vers2, vers3, totalVersement, dateLivraison, dateContrat, versRegularis,
                    tropPerçuRegle, tropPerçuNonRegle, resteAPayer, adresse, telephone, totalAvances
            );
        }

        workbook.close();
        // ✅ Exécuter le package SRG après l’import
        runSRGPackage();
        System.out.println("✅ Fichier SRG importé et package exécuté avec succès !");
    }

    public void runSRGPackage() {
        try {
            // 👉 Change ce chemin par le chemin exact vers ton package SRG
            String command = "DTExec /F \"C:\\Users\\DELL\\OneDrive\\Desktop\\Al Omrane\\SSIS_Packages\\SRGPackage.dtsx\"";

            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("✅ Package SRG exécuté avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Erreur lors de l'exécution du package SRG !");
        }
    }
    // importation des donnees commerciaux

    public void importFromExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Ignore header row

            // Lire les colonnes
            String email = getCellValueAsString(row.getCell(0)).trim();
            String codeOperation = getCellValueAsString(row.getCell(1)).trim();
            String operationName = getCellValueAsString(row.getCell(2)).trim();

            // 1. Vérifier si email est vide ou nul
            if (email.isEmpty()) {
                continue; // Ignorer cette ligne
            }

            // 🔍 Chercher utilisateur par email
            Optional<UtilisateurDetails> utilisateurOpt = utilisateurDetailsRepository.findByEmail(email);

            if (utilisateurOpt.isPresent()) {
                UtilisateurDetails utilisateur = utilisateurOpt.get();

                // 2. Vérifier si l'opération existe déjà pour cet utilisateur
                boolean operationExists = operationRepository.existsByUtilisateurDetailsAndCodeOperationAndOperation(
                        utilisateur, codeOperation, operationName
                );

                if (operationExists) {
                    continue; // Déjà existante → ignorer
                }

                // 3. Sinon, créer et sauvegarder l'opération
                Operation operation = new Operation();
                operation.setCodeOperation(codeOperation);
                operation.setOperation(operationName);
                operation.setUtilisateurDetails(utilisateur);

                operationRepository.save(operation);

            } else {
                // Si utilisateur inexistant, ignorer ou lever une exception si tu veux arrêter
                throw new RuntimeException("❌ Utilisateur non trouvé pour l'email : " + email);
            }
        }

        workbook.close();
    }

    // ✅ Utilitaire pour gérer les cellules vides ou numériques proprement
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // ✅ Correction : Méthode pour lire les cellules String
    private String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }


}
