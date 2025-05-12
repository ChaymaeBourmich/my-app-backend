package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;


import jakarta.persistence.*;
import java.util.Date;

@Entity(name = "SRG_DB")   // <- le nom à utiliser dans les requêtes JPQL
@Table(name = "SRG_DB")
public class SRG {

	@Id
	@Column(name = "Dossier") // je te conseille d'utiliser Dossier comme PK si c'est unique
	private String dossier;

	@Column(name = "Article_AV")
	private String articleAv;

	@Column(name = "Opération")
	private String operation;

	@Column(name = "Division")
	private String division;

	@Column(name = "Déscription")
	private String description;

	@Column(name = "Désignation")
	private String designation;

	@Column(name = "Type unité")
	private String typeUnite;

	@Column(name = "Type produit")
	private String typeProduit;

	@Column(name = "Nombre unité")
	private Double nombreUnite;

	@Column(name = "Article")
	private String article;

	@Column(name = "Statut ADV")
	private String statutAdv;

	@Column(name = "Désignation statut ADV")
	private String designationStatutAdv;

	@Column(name = "Statut de vente")
	private String statutDeVente;

	@Column(name = "N° Lot")
	private String noLot;

	@Column(name = "Superficie")
	private Double superficie;

	@Column(name = "Prix unitaire")
	private Double prixUnitaire;

	@Column(name = "Prix totale")
	private Double prixTotale;

	@Column(name = "Titre foncier")
	private String titreFoncier;

	@Column(name = "[Numéro Client]")
	private String numeroClient;

	@Column(name = "Client")
	private String client;

	@Column(name = "Titre de civilité")
	private String titreDeCivilite;

	@Column(name = "CIN/RC")
	private String cinRc;

	@Column(name = "Type document")
	private String typeDocument;

	@Column(name = "Désignation type de document")
	private String designationTypeDocument;

	@Column(name = "Création")
	private Date dateCreation;

	@Column(name = "Vers1")
	private Double vers1;

	@Column(name = "Vers2")
	private Double vers2;

	@Column(name = "Vers3")
	private Double vers3;

	@Column(name = "Total versement")
	private Double totalVersement;

	@Column(name = "Date livraison")
	private Date dateLivraison;

	@Column(name = "Date contrat")
	private Date dateContrat;

	@Column(name = "Vers regularis")
	private Double versRegularis;

	@Column(name = "Trop perçu réglé")
	private Double tropPerçuRegle;

	@Column(name = "Trop perçu non réglé")
	private Double tropPerçuNonRegle;

	@Column(name = "Reste à payer")
	private Double resteAPayer;

	@Column(name = "Adresse")
	private String adresse;

	@Column(name = "Telephone")
	private String telephone;

	@Column(name = "Total avances")
	private Double totalAvances;


	public String getDossier() {
		return dossier;
	}

	public void setDossier(String dossier) {
		this.dossier = dossier;
	}

	public String getArticleAv() {
		return articleAv;
	}

	public void setArticleAv(String articleAv) {
		this.articleAv = articleAv;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTypeUnite() {
		return typeUnite;
	}

	public void setTypeUnite(String typeUnite) {
		this.typeUnite = typeUnite;
	}

	public String getTypeProduit() {
		return typeProduit;
	}

	public void setTypeProduit(String typeProduit) {
		this.typeProduit = typeProduit;
	}

	public Double getNombreUnite() {
		return nombreUnite;
	}

	public void setNombreUnite(Double nombreUnite) {
		this.nombreUnite = nombreUnite;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getStatutAdv() {
		return statutAdv;
	}

	public void setStatutAdv(String statutAdv) {
		this.statutAdv = statutAdv;
	}

	public String getDesignationStatutAdv() {
		return designationStatutAdv;
	}

	public void setDesignationStatutAdv(String designationStatutAdv) {
		this.designationStatutAdv = designationStatutAdv;
	}

	public String getStatutDeVente() {
		return statutDeVente;
	}

	public void setStatutDeVente(String statutDeVente) {
		this.statutDeVente = statutDeVente;
	}

	public String getNoLot() {
		return noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public Double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getPrixTotale() {
		return prixTotale;
	}

	public void setPrixTotale(Double prixTotale) {
		this.prixTotale = prixTotale;
	}

	public String getTitreFoncier() {
		return titreFoncier;
	}

	public void setTitreFoncier(String titreFoncier) {
		this.titreFoncier = titreFoncier;
	}

	public String getNumeroClient() {
		return numeroClient;
	}

	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTitreDeCivilite() {
		return titreDeCivilite;
	}

	public void setTitreDeCivilite(String titreDeCivilite) {
		this.titreDeCivilite = titreDeCivilite;
	}

	public String getCinRc() {
		return cinRc;
	}

	public void setCinRc(String cinRc) {
		this.cinRc = cinRc;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getDesignationTypeDocument() {
		return designationTypeDocument;
	}

	public void setDesignationTypeDocument(String designationTypeDocument) {
		this.designationTypeDocument = designationTypeDocument;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Double getVers1() {
		return vers1;
	}

	public void setVers1(Double vers1) {
		this.vers1 = vers1;
	}

	public Double getVers2() {
		return vers2;
	}

	public void setVers2(Double vers2) {
		this.vers2 = vers2;
	}

	public Double getVers3() {
		return vers3;
	}

	public void setVers3(Double vers3) {
		this.vers3 = vers3;
	}

	public Double getTotalVersement() {
		return totalVersement;
	}

	public void setTotalVersement(Double totalVersement) {
		this.totalVersement = totalVersement;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Date getDateContrat() {
		return dateContrat;
	}

	public void setDateContrat(Date dateContrat) {
		this.dateContrat = dateContrat;
	}

	public Double getVersRegularis() {
		return versRegularis;
	}

	public void setVersRegularis(Double versRegularis) {
		this.versRegularis = versRegularis;
	}

	public Double getTropPerçuRegle() {
		return tropPerçuRegle;
	}

	public void setTropPerçuRegle(Double tropPerçuRegle) {
		this.tropPerçuRegle = tropPerçuRegle;
	}

	public Double getTropPerçuNonRegle() {
		return tropPerçuNonRegle;
	}

	public void setTropPerçuNonRegle(Double tropPerçuNonRegle) {
		this.tropPerçuNonRegle = tropPerçuNonRegle;
	}

	public Double getResteAPayer() {
		return resteAPayer;
	}

	public void setResteAPayer(Double resteAPayer) {
		this.resteAPayer = resteAPayer;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Double getTotalAvances() {
		return totalAvances;
	}

	public void setTotalAvances(Double totalAvances) {
		this.totalAvances = totalAvances;
	}
}