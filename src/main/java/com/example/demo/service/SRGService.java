package com.example.demo.service;


import com.example.demo.model.SRG;
import com.example.demo.repository.SRGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SRGService {

    @Autowired
    private SRGRepository srgRepository;

    public List<SRG> getAllSrgData() {
        return srgRepository.findAll();  
    }
}
