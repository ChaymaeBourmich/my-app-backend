package com.example.demo.service;

import com.example.demo.model.AppelDeFond;
import com.example.demo.model.Recouvrement;
import java.util.List;

public interface RecouvrementService {
    List<Recouvrement> getAllRecouvrementData();

    List<Recouvrement> getDossiersEnEtatJuridique();

    List<Recouvrement> getRecouvrementPourUser(Long userId);
}
