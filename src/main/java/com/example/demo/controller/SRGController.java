package com.example.demo.controller;


import com.example.demo.model.SRG;
import com.example.demo.service.SRGService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "https://my-app-frontend-cq7c.vercel.app")

@RestController
public class SRGController {

    @Autowired
    private SRGService srgService;

    @GetMapping("/srg")
    public List<SRG> getSrgData() {
        return srgService.getAllSrgData();  
    }
}

