package com.example.demo.config;

import com.example.demo.model.Recouvrement;
import com.example.demo.repository.RecouvrementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataReader implements CommandLineRunner {

    @Autowired
    private RecouvrementRepository recouvrementRepository;

    @Override
    public void run(String... args) throws Exception {
        Iterable<Recouvrement> recouvrements = recouvrementRepository.findAll();
        recouvrements.forEach(System.out::println);
    }
}
