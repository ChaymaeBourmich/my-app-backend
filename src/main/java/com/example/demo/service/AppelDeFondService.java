package com.example.demo.service;


import com.example.demo.model.AppelDeFond;
import com.example.demo.repository.AppelDeFondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppelDeFondService {

    @Autowired
    private AppelDeFondRepository repository;

    public List<AppelDeFond> getAllAppels() {
        return repository.findAll();
    }




}
