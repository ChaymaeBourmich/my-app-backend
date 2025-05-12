package com.example.demo.controller;

import com.example.demo.model.Echelonnement;
import com.example.demo.service.EchelonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "https://my-app-frontend-cq7c.vercel.app")

@RestController
@RequestMapping("/api/echelonnements")
public class EchelonnementController {

    @Autowired
    private EchelonnementService echelonnementService;

    @PostMapping("/add")
    public ResponseEntity<Echelonnement> ajouterEchelonnement(
            @RequestBody Echelonnement echelonnement,
            @RequestParam String relanceId,
            @RequestParam String numeroClient) {

        Echelonnement savedEchelonnement = echelonnementService.saveEchelonnement(echelonnement, relanceId, numeroClient);
        return ResponseEntity.ok(savedEchelonnement);
    }


}
