package com.example.demo.controller;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Klasse für die Geschäftsführer

import com.example.demo.model.entities.Mitarbeiter;
import com.example.demo.model.entities.Mitglied;
import com.example.demo.service.GeschaeftsfuehrerService;
import com.example.demo.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geschaeftsfuehrer")

public class GeschaeftsfuehrerController {

    private final GeschaeftsfuehrerService geschaeftsfuehrerService;

    @Autowired
    public GeschaeftsfuehrerController(GeschaeftsfuehrerService geschaeftsfuehrerService) {
        this.geschaeftsfuehrerService = geschaeftsfuehrerService;
    }

    // Mitarbeiter hinzufügen
    @PostMapping("/mitarbeiter")
    public ResponseEntity<Mitarbeiter> mitarbeiterHinzufuegen(@RequestBody Mitarbeiter mitarbeiter) {
        try {
            Mitarbeiter neuerMitarbeiter = geschaeftsfuehrerService.mitarbeiterHinzufuegen(mitarbeiter);
            return ResponseEntity.ok(neuerMitarbeiter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Mitarbeiter bearbeiten
    @PutMapping("/mitarbeiter/{id}")
    public ResponseEntity<Mitarbeiter> mitarbeiterBearbeiten(@PathVariable Long id, @RequestBody Mitarbeiter mitarbeiter) {
        try {
            Mitarbeiter bearbeiteterMitarbeiter = geschaeftsfuehrerService.mitarbeiterBearbeiten(id, mitarbeiter);
            return ResponseEntity.ok(bearbeiteterMitarbeiter);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Mitarbeiter löschen
    @DeleteMapping("/mitarbeiter/{id}")
    public ResponseEntity<Void> mitarbeiterLoeschen(@PathVariable Long id) {
        try {
            geschaeftsfuehrerService.mitarbeiterLoeschen(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mitglied hinzufügen
    @PostMapping("/mitglied")
    public ResponseEntity<Mitglied> mitgliedHinzufuegen(@RequestBody Mitglied mitglied) {
        try {
            Mitglied neuesMitglied = geschaeftsfuehrerService.mitgliedHinzufuegen(mitglied);
            return ResponseEntity.ok(neuesMitglied);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Mitglied bearbeiten
    @PutMapping("/mitglied/{id}")
    public ResponseEntity<Mitglied> mitgliedBearbeiten(@PathVariable Long id, @RequestBody Mitglied mitglied) {
        try {
            Mitglied bearbeitetesMitglied = geschaeftsfuehrerService.mitgliedBearbeiten(id, mitglied);
            return ResponseEntity.ok(bearbeitetesMitglied);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Mitglied löschen
    @DeleteMapping("/mitglied/{id}")
    public ResponseEntity<Void> mitgliedLoeschen(@PathVariable Long id) {
        try {
            geschaeftsfuehrerService.mitgliedLoeschen(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

