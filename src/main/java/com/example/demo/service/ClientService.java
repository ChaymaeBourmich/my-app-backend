package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Relance;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RelanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RelanceRepository relanceRepository;


    //********************************************************************
    public Client getClientByNom(String nom) {
        return clientRepository.findByNom(nom)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec ce nom"));
    }

    //********************************************************************

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    //********************************************************************

    public List<Client> findClientsByUserId(Long userId) {
        List<Relance> relances = relanceRepository.findByUserId(userId);

        // Utiliser un Set pour éviter les doublons
        Set<Client> clients = relances.stream()
                .map(Relance::getNumClient)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        return new ArrayList<>(clients);
    }

    //********************************************************************

    public Client updateClientInfo(String numeroClient, Client clientToUpdate) {
        Client existing = clientRepository.findByNumeroClient(numeroClient)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec le CIN : " + numeroClient));

        existing.setCin2(clientToUpdate.getCin2());
        existing.setNom_prenom2(clientToUpdate.getNom_prenom2());
        existing.setAdresse2(clientToUpdate.getAdresse2());
        existing.setTelephone2(clientToUpdate.getTelephone2());
        existing.setEmail2(clientToUpdate.getEmail2());

        return clientRepository.save(existing);
    }
}
