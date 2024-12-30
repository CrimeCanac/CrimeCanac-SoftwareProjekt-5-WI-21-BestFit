package com.example.demo.service;
// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Klasse für die Geschäftsführer

import com.example.demo.model.entities.Mitarbeiter;
import com.example.demo.model.entities.Mitglied;
import com.example.demo.model.repositories.MitarbeiterRepository;
import com.example.demo.model.repositories.MitgliedRepository;
import com.example.demo.exception.EntityNotFoundException;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeschaeftsfuehrerService {
    private final MitarbeiterRepository mitarbeiterRepository;
    private final MitgliedRepository mitgliedRepository;

    @Autowired
    public GeschaeftsfuehrerService(MitarbeiterRepository mitarbeiterRepository,
                                    MitgliedRepository mitgliedRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.mitgliedRepository = mitgliedRepository;
    }

     // Methode zum Abrufen aller Mitarbeiter
    public List<Mitarbeiter> alleMitarbeiterAbrufen() {
        return mitarbeiterRepository.findAll();
    }

    // Mitarbeiter hinzufügen
    public Mitarbeiter mitarbeiterHinzufuegen(Mitarbeiter mitarbeiter) {
        return mitarbeiterRepository.save(mitarbeiter);
    }

    // Mitarbeiter bearbeiten
    public Mitarbeiter mitarbeiterBearbeiten(Long id, Mitarbeiter mitarbeiter) {
        Optional<Mitarbeiter> vorhandenerMitarbeiter = mitarbeiterRepository.findById(id);
        if (vorhandenerMitarbeiter.isPresent()) {
            mitarbeiter.setId(id);
            return mitarbeiterRepository.save(mitarbeiter);
        } else {
            throw new EntityNotFoundException("Mitarbeiter", id);
        }
    }

    // Mitarbeiter löschen
    public void mitarbeiterLoeschen(Long id) {
        mitarbeiterRepository.deleteById(id);
    }

    // Methode zum Abrufen aller Mitglieder
    public List<Mitglied> alleMitgliederAbrufen() {
        return mitgliedRepository.findAll();
    }
    
    // Mitglied hinzufügen
    public Mitglied mitgliedHinzufuegen(Mitglied mitglied) {
        return mitgliedRepository.save(mitglied);
    }

    // Mitglied bearbeiten
    public Mitglied mitgliedBearbeiten(Long id, Mitglied mitglied) {
        Optional<Mitglied> vorhandenesMitglied = mitgliedRepository.findById(id);
        if (vorhandenesMitglied.isPresent()) {
            mitglied.setId(id);
            return mitgliedRepository.save(mitglied);
        } else {
            throw new EntityNotFoundException("Mitglied", id);
        }
    }

    // Mitglied löschen
    public void mitgliedLoeschen(Long id) {
        mitgliedRepository.deleteById(id);
    }
}
