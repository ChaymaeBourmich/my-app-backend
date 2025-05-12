package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "https://my-app-frontend-cq7c.vercel.app")

public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;



    @GetMapping("/by-nom")
    public ResponseEntity<?> getClientByNom(@RequestParam String nom) {
        try {
            Client client = clientService.getClientByNom(nom);
            return ResponseEntity.ok(client);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("❌ Client non trouvé : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Erreur interne : " + e.getMessage());
        }
    }
    //********************************************************************************************
    @GetMapping("/by-user")
    public List<Client> getClientsByUser(@RequestParam Long userId) {
        return clientService.findClientsByUserId(userId);
    }

    //********************************************************************************************


    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
    //********************************************************************************************

    @PutMapping("/update/{numeroClient}")
    public ResponseEntity<?> updateClient(@PathVariable String numeroClient, @RequestBody Map<String, Object> updates) {
        Client client = clientRepository.findById(numeroClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec le numéro client : " + numeroClient));

        // Maintenant on met à jour seulement les champs _2 s'ils existent
        if (updates.containsKey("cin2")) {
            client.setCin2((String) updates.get("cin2"));
        }
        if (updates.containsKey("nom_prenom2")) {
            client.setNom_prenom2((String) updates.get("nom_prenom2"));
        }
        if (updates.containsKey("adresse2")) {
            client.setAdresse2((String) updates.get("adresse2"));
        }
        if (updates.containsKey("telephone2")) {
            client.setTelephone2((String) updates.get("telephone2"));
        }
        if (updates.containsKey("email2")) {
            client.setEmail2((String) updates.get("email2"));
        }

        clientRepository.save(client);

        return ResponseEntity.ok("✅ Client mis à jour avec succès !");
    }






}
