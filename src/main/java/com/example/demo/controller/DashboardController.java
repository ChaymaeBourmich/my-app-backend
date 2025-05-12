package com.example.demo.controller;

import com.example.demo.DTO.AdminDashDTO;
import com.example.demo.DTO.AdminTotauxDTO;
import com.example.demo.DTO.RecouvrementStatsDTO;
import com.example.demo.model.User;
import com.example.demo.model.UtilisateurDetails;
import com.example.demo.repository.RecouvrementRepository;
import com.example.demo.repository.RelanceRepository;
import com.example.demo.repository.UtilisateurDetailsRepository;
import com.example.demo.service.DashboardService;
import com.example.demo.service.RecouvrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "https://my-app-frontend-beta.vercel.app")

@RestController
public class DashboardController {

    @Autowired
    private UtilisateurDetailsRepository utilisateurDetailsRepository;

    @Autowired
    private RelanceRepository relanceRepository;

    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private RecouvrementRepository recouvrementRepository;


    @GetMapping("/recouvrement-commercial")
    public RecouvrementStatsDTO getStatsPourCommercial(Authentication authentication) {
        String email = authentication.getName();

        UtilisateurDetails utilisateurDetails = utilisateurDetailsRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        User user = utilisateurDetails.getUser();
        if (user == null) {
            throw new RuntimeException("Lien entre UtilisateurDetails et User manquant !");
        }

        return dashboardService.calculerStatsPourUser(user);
    }
    @GetMapping("/par-commercial")
    public List<AdminDashDTO> getStatsParCommercial() {
        return dashboardService.calculerStatsParCommercial();
    }
    @GetMapping("/stats-globales")
    public ResponseEntity<AdminTotauxDTO> getStatsGlobales() {
        AdminTotauxDTO totaux = dashboardService.calculerTotaux();
        return ResponseEntity.ok(totaux);
    }

}
