package com.example.demo.service;

import com.example.demo.DTO.OperationRequest;
import com.example.demo.DTO.UtilisateurRequest;
import com.example.demo.model.Operation;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UtilisateurDetails;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.OperationRepository;
import com.example.demo.repository.UtilisateurDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UtilisateurDetailsRepository utilisateurDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void addUserWithDetails(
            String username,
            String password,
            Set<Role> roles,
            String nom,
            String prenom,
            String email,
            String telephone,
            String agence,
            String type,
            List<OperationRequest> operations // ⚡ Corrigé ici : pas List<String> mais List<OperationRequest>
    ) {

        // 1. Créer l'utilisateur
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode("123456")); // mot de passe par défaut
        user.setPremiereConnexion(true);
        user.setRoles(roles);
        userRepo.save(user);

        // 2. Créer les détails utilisateur
        UtilisateurDetails details = new UtilisateurDetails();
        details.setNom(nom);
        details.setPrenom(prenom);
        details.setEmail(email);
        details.setTelephone(telephone);
        details.setAgence(agence);
        details.setType(type);
        details.setUser(user);
        utilisateurDetailsRepository.save(details);

        // 3. Créer les opérations liées au profil
        if (operations != null && !operations.isEmpty()) {
            for (OperationRequest opReq : operations) {
                Operation operation = new Operation();
                operation.setOperation(opReq.getOperationName());     // ✅ Nom de l'opération
                operation.setCodeOperation(opReq.getCodeOperation()); // ✅ Code de l'opération
                operation.setUtilisateurDetails(details);             // ✅ Lien vers UtilisateurDetails
                operationRepository.save(operation);
            }
        }
    }






    public void deleteUser(Long id) {
        try {
            User user = userRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            userRepo.delete(user);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 Affiche bien l'erreur dans la console
            throw new RuntimeException("Erreur lors de la suppression : " + e.getMessage());
        }
    }






    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔐 Tentative de connexion pour : " + username);

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("❌ Aucun utilisateur trouvé avec le nom : " + username);
                    return new UsernameNotFoundException("Utilisateur non trouvé avec le nom : " + username);
                });

        System.out.println("✅ Utilisateur trouvé : ID = " + user.getId() + ", rôle(s) = " + user.getRoles());

        return user;
    }



    public Map<String, Object> getConnectedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 🔍 Rechercher l'utilisateur
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // 🔍 Rechercher les détails liés
        UtilisateurDetails details = utilisateurDetailsRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Détails utilisateur non trouvés"));

        // 🧠 Préparer les données à renvoyer
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("roles", user.getRoles());
        data.put("details", details);
        System.out.println("📤 Données retournées au frontend : " + data);

        return data;
    }



    @Transactional
    public void updateUser(Long utilisateurId, UtilisateurRequest request) {
        try {
            System.out.println("🟢 updateUser started pour ID: " + utilisateurId);

            User user = userRepo.findById(utilisateurId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            Optional<User> existing = userRepo.findByUsernameExcludingId(request.getEmail(), utilisateurId);
            if (existing.isPresent()) {
                throw new RuntimeException("❌ Email déjà utilisé par un autre utilisateur !");
            }

            // Mise à jour des infos User
            user.setUsername(request.getEmail());
            if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }

            // ⚠️ Utiliser un set mutable pour éviter l'erreur d'immuabilité
            user.setRoles(new HashSet<>(Set.of(request.getRole())));

            // Mise à jour UtilisateurDetails
            UtilisateurDetails details = utilisateurDetailsRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("Détails utilisateur non trouvés"));

            details.setNom(request.getNom());
            details.setPrenom(request.getPrenom());
            details.setEmail(request.getEmail());
            details.setTelephone(request.getTelephone());
            details.setAgence(request.getAgence());
            details.setType(request.getType());

            // ✅ Suppression explicite des anciennes opérations
            List<Operation> anciennes = operationRepository.findByUtilisateurDetails(details);
            operationRepository.deleteAll(anciennes);
            details.getOperations().clear();

            // Ajout des nouvelles opérations
            if (request.getOperations() != null) {
                for (OperationRequest opRequest : request.getOperations()) {
                    if (opRequest != null && opRequest.getOperationName() != null && !opRequest.getOperationName().trim().isEmpty()) {
                        Operation op = new Operation();
                        op.setOperation(opRequest.getOperationName()); // nom de l'opération
                        op.setCodeOperation(opRequest.getCodeOperation()); // code de l'opération
                        op.setUtilisateurDetails(details); // lien avec le user

                        details.getOperations().add(op); // ajoute à la liste
                    }
                }
            }


            utilisateurDetailsRepository.save(details);
            userRepo.save(user);

            System.out.println("✅ Utilisateur mis à jour avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("💥 Erreur dans updateUser: " + e.getMessage());
        }
    }
    public void resetPassword(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + id));

        user.setPassword(passwordEncoder.encode("123456")); // mot de passe par défaut
        user.setPremiereConnexion(true); // pour forcer le changement à la première connexion

        userRepo.save(user);
    }

}