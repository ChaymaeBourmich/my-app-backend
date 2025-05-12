package com.example.demo.model;

import com.example.demo.model.UtilisateurDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;

    @Column(name = "code_operation") // 👈 Base de données = code_operation
    private String codeOperation;     // 👈 Java = codeOperation (camelCase)

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonBackReference
    private UtilisateurDetails utilisateurDetails;

    // --- GETTERS & SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public String getCodeOperation() { return codeOperation; }
    public void setCodeOperation(String codeOperation) { this.codeOperation = codeOperation; }

    public UtilisateurDetails getUtilisateurDetails() { return utilisateurDetails; }
    public void setUtilisateurDetails(UtilisateurDetails utilisateurDetails) { this.utilisateurDetails = utilisateurDetails; }
}
