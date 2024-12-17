package com.example.demo.service;
// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Klasse für die Mitglieder

import com.example.demo.model.entities.Mitglied;
import com.example.demo.model.repositories.MitgliedRepository;
import com.example.demo.exception.EntityNotFoundException;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MitgliedService {
    private final MitgliedRepository mitgliedRepository;

    @Autowired
    public MitgliedService(MitgliedRepository mitgliedRepository) {
        this.mitgliedRepository = mitgliedRepository;
    }

    // Alle Mitglieder abfragen
    public List<Mitglied> getAlleMitglieder() {
        return mitgliedRepository.findAll();
    }

    // Mitglied nach ID abfragen
    public Mitglied getMitgliedById(Long id) {
        Optional<Mitglied> mitgliedOptional = mitgliedRepository.findById(id);
        if (mitgliedOptional.isPresent()) {
            return mitgliedOptional.get();
        } else {
            throw new EntityNotFoundException("Mitglied nicht gefunden mit ID: " , id);
        }
    }

    // Ein Mitglied hinzufügen (nur für Administratoren, Geschäftsführer und Mitarbeiter)
    public Mitglied mitgliedHinzufuegen(Mitglied mitglied) {
        return mitgliedRepository.save(mitglied);
    }
}
