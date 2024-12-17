package com.example.demo.controller;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Kontrollklasse für Mitarbeiter

import com.example.demo.model.entities.Mitglied;
import com.example.demo.service.MitarbeiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mitarbeiter")

public class MitarbeiterController {
    private final MitarbeiterService mitarbeiterService;

    public MitarbeiterController(MitarbeiterService mitarbeiterService) {
        this.mitarbeiterService = mitarbeiterService;
    }

    // Mitglied hinzufügen
    @PostMapping
    public ResponseEntity<Mitglied> mitgliedHinzufuegen(@RequestBody Mitglied mitglied) {
        return ResponseEntity.ok(mitarbeiterService.mitgliedHinzufuegen(mitglied));
    }

    // Mitglied bearbeiten
    @PutMapping("/{id}")
    public ResponseEntity<Mitglied> mitgliedBearbeiten(@PathVariable Long id, @RequestBody Mitglied mitglied) {
        return ResponseEntity.ok(mitarbeiterService.mitgliedBearbeiten(id, mitglied));
    }

    // Mitglied löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> mitgliedLoeschen(@PathVariable Long id) {
        mitarbeiterService.mitgliedLoeschen(id);
        return ResponseEntity.noContent().build();
    }
}
