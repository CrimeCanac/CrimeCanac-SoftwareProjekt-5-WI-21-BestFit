package com.example.demo.service;

// Written by: Ömer Yalcinkaya
// Created: 2024-12-12
// Description: Service-Schicht für die Verwaltung von Trainingsplan-Entitäten

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entities.Trainingsplan;
import com.example.demo.model.repositories.TrainingsplanRepository;

@Service
public class TrainingsplanService {


    private final TrainingsplanRepository repo;


    //Konstruktor
    @Autowired
    public TrainingsplanService(TrainingsplanRepository repo) {
        this.repo = repo;
    }

    public List<Trainingsplan> getAllTrainingsplaene() {
        return this.repo.findAll();
    }

    public long getAnzahlTrainingsplaene() {
        return this.repo.count();
    }

    public Trainingsplan findTrainingsplanById(long id) {
        return this.repo.findById(id).orElse(null);
    }

    public List<Trainingsplan> filterTrainingsplanByName(String name) {
        List<Trainingsplan> list = this.repo.filterTrainingsplanByName(name);
        return list;
    }
}
