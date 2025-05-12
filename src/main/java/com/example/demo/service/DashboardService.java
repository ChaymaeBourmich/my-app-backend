package com.example.demo.service;

import com.example.demo.DTO.AdminDashDTO;
import com.example.demo.DTO.AdminTotauxDTO;
import com.example.demo.DTO.RecouvrementStatsDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UtilisateurDetails;
import com.example.demo.repository.PaiementRepository;
import com.example.demo.repository.RecouvrementRepository;
import com.example.demo.repository.RelanceRepository;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private RelanceRepository relanceRepository;

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RecouvrementRepository recouvrementRepository;

    // ➤ Statistiques individuelles pour un commercial
    public RecouvrementStatsDTO calculerStatsPourUser(User user) {
        Double total = relanceRepository.sumMontantByUser(user);
        Double recouvert = paiementRepository.sumMontantByUser(user);

        total = (total != null) ? total : 0.0;
        recouvert = (recouvert != null) ? recouvert : 0.0;

        double reste = total - recouvert;
        double taux = (total > 0) ? (recouvert * 100.0 / total) : 0.0;

        return new RecouvrementStatsDTO(total, recouvert, reste, taux);
    }

    // ➤ Statistiques globales par commercial (pour tableau admin)
    public List<AdminDashDTO> calculerStatsParCommercial() {
        List<User> commerciaux = userRepo.findAllByRole(Role.COMMERCIAL);
        List<AdminDashDTO> result = new ArrayList<>();

        for (User user : commerciaux) {
            UtilisateurDetails details = user.getUtilisateurDetails();
            if (details == null) continue;

            Double total = relanceRepository.sumMontantByUser(user);
            Double recouvert = paiementRepository.sumMontantByUser(user);

            total = (total != null) ? total : 0.0;
            recouvert = (recouvert != null) ? recouvert : 0.0;

            double reste = total - recouvert;
            double taux = (total > 0) ? (recouvert * 100.0 / total) : 0.0;

            AdminDashDTO dto = new AdminDashDTO();
            dto.setAgence(details.getAgence());
            dto.setCommercial(details.getPrenom() + " " + details.getNom());
            dto.setMontantARecouvrer(total);
            dto.setMontantRecouvert(recouvert);
            dto.setMontantRestant(reste);
            dto.setTauxRecouvrement(taux);

            result.add(dto);
        }

        return result;
    }

    // ➤ Totaux globaux pour les cartes
    public AdminTotauxDTO calculerTotaux() {
        Double montantTotal = recouvrementRepository.sumMontantGlobal();
        Double montantRecouvert = paiementRepository.sumMontantGlobal();

        montantTotal = (montantTotal != null) ? montantTotal : 0.0;
        montantRecouvert = (montantRecouvert != null) ? montantRecouvert : 0.0;

        double montantRestant = montantTotal - montantRecouvert;
        double tauxRecouvrement = (montantTotal > 0) ? (montantRecouvert * 100.0 / montantTotal) : 0.0;

        return new AdminTotauxDTO(
                montantTotal,
                montantRecouvert,
                montantRestant,
                tauxRecouvrement
        );
    }
}
