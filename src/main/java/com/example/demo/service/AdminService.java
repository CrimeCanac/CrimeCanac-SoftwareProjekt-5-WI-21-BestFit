package com.example.demo.service;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Klasse für die Administrator

import com.example.demo.model.entities.Geschaeftsfuehrer;
import com.example.demo.model.entities.Mitarbeiter;
import com.example.demo.model.entities.Mitglied;
import com.example.demo.model.repositories.GeschaeftsfuehrerRepository;
import com.example.demo.model.repositories.MitarbeiterRepository;
import com.example.demo.model.repositories.MitgliedRepository;
import com.example.demo.exception.EntityNotFoundException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final GeschaeftsfuehrerRepository geschaeftsfuehrerRepository;
    private final MitarbeiterRepository mitarbeiterRepository;
    private final MitgliedRepository mitgliedRepository;

    @Autowired
    public AdminService(GeschaeftsfuehrerRepository geschaeftsfuehrerRepository,
                        MitarbeiterRepository mitarbeiterRepository,
                        MitgliedRepository mitgliedRepository) {
        this.geschaeftsfuehrerRepository = geschaeftsfuehrerRepository;
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.mitgliedRepository = mitgliedRepository;
    }

    // Methode zum Abrufen aller Geschaeftsfuehrer
    public List<Geschaeftsfuehrer> alleGeschaeftsfuehrerAbrufen() {
        return geschaeftsfuehrerRepository.findAll();
    }

    // Geschaeftsfuehrer hinzufügen
    public Geschaeftsfuehrer geschaeftsfuehrerHinzufuegen(Geschaeftsfuehrer geschaeftsfuehrer) {
        return geschaeftsfuehrerRepository.save(geschaeftsfuehrer);
    }

    // Geschaeftsfuehrer bearbeiten
    public Geschaeftsfuehrer geschaeftsfuehrerBearbeiten(Long id, Geschaeftsfuehrer geschaeftsfuehrer) {
        if (geschaeftsfuehrerRepository.existsById(id)) {
            geschaeftsfuehrer.setId(id);
            return geschaeftsfuehrerRepository.save(geschaeftsfuehrer);
        } else {
            throw new EntityNotFoundException("Geschaeftsfuehrer", id);
        }
    }

    // Geschaeftsfuehrer löschen
    public void geschaeftsfuehrerLoeschen(Long id) {
        geschaeftsfuehrerRepository.deleteById(id);
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
        if (mitarbeiterRepository.existsById(id)) {
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
        if (mitgliedRepository.existsById(id)) {
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
