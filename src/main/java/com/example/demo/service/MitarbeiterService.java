package com.example.demo.service;
// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Klasse für die Mitarbeiter

import com.example.demo.model.entities.Mitglied;
import com.example.demo.model.repositories.MitgliedRepository;
import com.example.demo.exception.EntityNotFoundException;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MitarbeiterService {
    private final MitgliedRepository mitgliedRepository;

    @Autowired
    public MitarbeiterService(MitgliedRepository mitgliedRepository) {
        this.mitgliedRepository = mitgliedRepository;
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
