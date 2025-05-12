package com.example.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commercial")
public class CommercialController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('COMMERCIAL')")
    public String commercialDashboard() {
        return "Welcome, Commercial User!";
    }
}

