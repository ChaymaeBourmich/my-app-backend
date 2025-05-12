package com.example.demo.controller;

import com.example.demo.DTO.ChangePasswordRequest;
import com.example.demo.DTO.OperationRequest;
import com.example.demo.DTO.UtilisateurRequest;
import com.example.demo.model.Operation;
import com.example.demo.model.User;
import com.example.demo.model.UtilisateurDetails;
import com.example.demo.model.Role;
import com.example.demo.repository.OperationRepository;
import com.example.demo.repository.UtilisateurDetailsRepository;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://my-app-frontend-cq7c.vercel.app")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UtilisateurDetailsRepository utilisateurDetailsRepository;

    @Autowired
    private UserRepo userRepository;


    @Autowired
    private OperationRepository operationRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/add")
    public ResponseEntity<?> addUserWithDetails(@RequestBody UtilisateurRequest request) {
        if (request.getRole() == null) {
            return ResponseEntity.badRequest().body("❌ Le champ 'role' est obligatoire.");
        }

        Set<Role> roles = Set.of(request.getRole()); // ✅ plus de .toUpperCase() ni conversion

        userService.addUserWithDetails(
                request.getUsername(),
                request.getPassword(),
                roles,
                request.getNom(),
                request.getPrenom(),
                request.getEmail(),
                request.getTelephone(),
                request.getAgence(),
                request.getType(),
                request.getOperations()
        );

        return ResponseEntity.ok("✅ Utilisateur ajouté avec succès !");
    }



    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        // Vérifier si l'ancien mot de passe est correct
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Ancien mot de passe incorrect.");
        }

        // Modifier le mot de passe et désactiver le flag de première connexion
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setPremiereConnexion(false);
        userRepository.save(user);

        return ResponseEntity.ok("✅ Mot de passe modifié avec succès !");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);  // Appelle le service qui supprime l'utilisateur
            return ResponseEntity.ok("Utilisateur supprimé avec succès !");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression : " + e.getMessage());
        }
    }



    @GetMapping("/all")
    public ResponseEntity<List<UtilisateurRequest>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UtilisateurRequest> responses = new ArrayList<>();

        for (User user : users) {
            UtilisateurDetails details = utilisateurDetailsRepository.findByUser(user).orElse(null);

            if (details != null) {
                UtilisateurRequest dto = new UtilisateurRequest();

                dto.setUtilisateurId(user.getId());
                dto.setUsername(user.getUsername());

                // ✅ Prendre le premier rôle (car 1 seul)
                Role role = user.getRoles().stream().findFirst().orElse(null);
                dto.setRole(role);

                dto.setNom(details.getNom());
                dto.setPrenom(details.getPrenom());
                dto.setEmail(details.getEmail());
                dto.setTelephone(details.getTelephone());
                dto.setAgence(details.getAgence());
                dto.setType(details.getType());

                // ✅ Corriger ici : construire une vraie liste d'OperationRequest
                List<OperationRequest> ops = details.getOperations().stream()
                        .map(operation -> {
                            OperationRequest opRequest = new OperationRequest();
                            opRequest.setOperationName(operation.getOperation());
                            opRequest.setCodeOperation(operation.getCodeOperation());
                            return opRequest;
                        })
                        .toList();
                dto.setOperations(ops);

                // ✅ Ajouter le DTO à la liste
                responses.add(dto);
            }
        }

        return ResponseEntity.ok(responses);
    }




    @PostMapping("/operations/add")
    public ResponseEntity<String> addOperation(@RequestBody Operation operation) {
        // Récupérer le profil utilisateur lié à l'opération
        UtilisateurDetails details = utilisateurDetailsRepository
                .findById(operation.getUtilisateurDetails().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur Details non trouvé"));

        // Lier et sauvegarder l’opération
        operation.setUtilisateurDetails(details);
        operationRepository.save(operation);

        return ResponseEntity.ok("✅ Opération ajoutée avec succès !");
    }


    @GetMapping("/user-details")
    public ResponseEntity<Map<String, Object>> getUserDetails(@AuthenticationPrincipal User user) {
        Optional<UtilisateurDetails> optionalDetails = utilisateurDetailsRepository.findByUser(user);

        if (optionalDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Utilisateur introuvable"));
        }

        UtilisateurDetails details = optionalDetails.get();

        Map<String, Object> response = new HashMap<>();
        response.put("roles", user.getRoles().stream().map(Enum::name).toList());
        response.put("details", details);

        return ResponseEntity.ok(response);
    }



    @PutMapping("/reset-password/{id}")
    public ResponseEntity<?> resetPassword(@PathVariable Long id) {
        userService.resetPassword(id); // met à jour le mot de passe et setPremiereConnexion(true)
        return ResponseEntity.ok("Mot de passe réinitialisé.");
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UtilisateurRequest request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok("Utilisateur mis à jour avec succès !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        UtilisateurDetails details = utilisateurDetailsRepository.findByUser(user)
                .orElse(null);

        if (details == null) return ResponseEntity.notFound().build();

        // 🔥 Récupérer toutes les opérations en tant qu'objets OperationRequest
        List<OperationRequest> ops = operationRepository.findByUtilisateurDetails(details)
                .stream()
                .map(op -> {
                    OperationRequest operationRequest = new OperationRequest();
                    operationRequest.setOperationName(op.getOperation());
                    operationRequest.setCodeOperation(op.getCodeOperation());
                    return operationRequest;
                })
                .toList();

        // 🔥 Remplir la réponse avec des OperationRequest, pas des String
        UtilisateurRequest response = new UtilisateurRequest();
        response.setUtilisateurId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRoles().stream().findFirst().orElse(null));
        response.setNom(details.getNom());
        response.setPrenom(details.getPrenom());
        response.setEmail(details.getEmail());
        response.setTelephone(details.getTelephone());
        response.setAgence(details.getAgence());
        response.setType(details.getType());
        response.setOperations(ops);  // 🚀 ici la bonne structure

        return ResponseEntity.ok(response);
    }






}
