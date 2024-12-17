package com.example.demo.controller;
// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Kontrollklasse für Mitglieder

import com.example.demo.model.entities.Mitglied;
import com.example.demo.service.MitgliedService;
import com.example.demo.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/mitglieder")
public class MitgliedController {

    private final MitgliedService mitgliedService;

    @Autowired
    public MitgliedController(MitgliedService mitgliedService) {
        this.mitgliedService = mitgliedService;
    }

    // Alle Mitglieder abfragen
    @GetMapping
    public ResponseEntity<List<Mitglied>> getAlleMitglieder() {
        List<Mitglied> mitglieder = mitgliedService.getAlleMitglieder();
        return new ResponseEntity<>(mitglieder, HttpStatus.OK);
    }

    // Mitglied nach ID abfragen
    @GetMapping("/{id}")
    public ResponseEntity<Mitglied> getMitgliedById(@PathVariable Long id) {
        try {
            Mitglied mitglied = mitgliedService.getMitgliedById(id);
            return new ResponseEntity<>(mitglied, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Ein Mitglied hinzufügen (nur für Administratoren, Geschäftsführer und Mitarbeiter)
    @PostMapping
    public ResponseEntity<Mitglied> mitgliedHinzufuegen(@RequestBody Mitglied mitglied) {
        Mitglied hinzugefuegtesMitglied = mitgliedService.mitgliedHinzufuegen(mitglied);
        return new ResponseEntity<>(hinzugefuegtesMitglied, HttpStatus.CREATED);
    }
}
