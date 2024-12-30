package com.example.demo.controller;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Klasse für die Administrator

import com.example.demo.model.entities.Geschaeftsfuehrer;
import com.example.demo.model.entities.Mitarbeiter;
import com.example.demo.model.entities.Mitglied;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")

public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Geschäftsführer hinzufügen
    @PostMapping("/geschaeftsfuehrer")
    public ResponseEntity<Geschaeftsfuehrer> geschaeftsfuehrerHinzufuegen(@RequestBody Geschaeftsfuehrer geschaeftsfuehrer) {
        Geschaeftsfuehrer hinzugefügterGeschaeftsfuehrer = adminService.geschaeftsfuehrerHinzufuegen(geschaeftsfuehrer);
        return ResponseEntity.ok(hinzugefügterGeschaeftsfuehrer);
    }

    // Geschäftsführer bearbeiten
    @PutMapping("/geschaeftsfuehrer/{id}")
    public ResponseEntity<Geschaeftsfuehrer> geschaeftsfuehrerBearbeiten(@PathVariable Long id, @RequestBody Geschaeftsfuehrer geschaeftsfuehrer) {
        Geschaeftsfuehrer bearbeiteterGeschaeftsfuehrer = adminService.geschaeftsfuehrerBearbeiten(id, geschaeftsfuehrer);
        return ResponseEntity.ok(bearbeiteterGeschaeftsfuehrer);
    }

    // Geschäftsführer löschen
    @DeleteMapping("/geschaeftsfuehrer/{id}")
    public ResponseEntity<Void> geschaeftsfuehrerLoeschen(@PathVariable Long id) {
        adminService.geschaeftsfuehrerLoeschen(id);
        return ResponseEntity.noContent().build();
    }

    // Mitarbeiter hinzufügen
    @PostMapping("/mitarbeiter")
    public ResponseEntity<Mitarbeiter> mitarbeiterHinzufuegen(@RequestBody Mitarbeiter mitarbeiter) {
        Mitarbeiter hinzugefügterMitarbeiter = adminService.mitarbeiterHinzufuegen(mitarbeiter);
        return ResponseEntity.ok(hinzugefügterMitarbeiter);
    }

    // Mitarbeiter bearbeiten
    @PutMapping("/mitarbeiter/{id}")
    public ResponseEntity<Mitarbeiter> mitarbeiterBearbeiten(@PathVariable Long id, @RequestBody Mitarbeiter mitarbeiter) {
        Mitarbeiter bearbeiteterMitarbeiter = adminService.mitarbeiterBearbeiten(id, mitarbeiter);
        return ResponseEntity.ok(bearbeiteterMitarbeiter);
    }

    // Mitarbeiter löschen
    @DeleteMapping("/mitarbeiter/{id}")
    public ResponseEntity<Void> mitarbeiterLoeschen(@PathVariable Long id) {
        adminService.mitarbeiterLoeschen(id);
        return ResponseEntity.noContent().build();
    }

    // Mitglied hinzufügen
    @PostMapping("/mitglied")
    public ResponseEntity<Mitglied> mitgliedHinzufuegen(@RequestBody Mitglied mitglied) {
        Mitglied hinzugefügtesMitglied = adminService.mitgliedHinzufuegen(mitglied);
        return ResponseEntity.ok(hinzugefügtesMitglied);
    }

    // Mitglied bearbeiten
    @PutMapping("/mitglied/{id}")
    public ResponseEntity<Mitglied> mitgliedBearbeiten(@PathVariable Long id, @RequestBody Mitglied mitglied) {
        Mitglied bearbeitetesMitglied = adminService.mitgliedBearbeiten(id, mitglied);
        return ResponseEntity.ok(bearbeitetesMitglied);
    }

    // Mitglied löschen
    @DeleteMapping("/mitglied/{id}")
    public ResponseEntity<Void> mitgliedLoeschen(@PathVariable Long id) {
        adminService.mitgliedLoeschen(id);
        return ResponseEntity.noContent().build();
    }
}
