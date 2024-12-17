package com.example.demo.model.entities;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description:Entität für die Geschäftsführer Rolle

import jakarta.persistence.*;

@Entity
@Table(name = "geschaeftsfuehrer")

public class Geschaeftsfuehrer extends User {
    @Column(name = "adresse", nullable = true)
    private String adresse;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "telefon", nullable = true)
    private String telefon;

    // Konstukturen
    public Geschaeftsfuehrer() {
        super();
        this.setRolle("geschaeftsfuehrer");   
    }

    public Geschaeftsfuehrer(String benutzername, String passwort, String vorname, String nachname, String sicherheitsfrageAntwort, String adresse, String email, String telefon) {
        super();        
        this.setBenutzername(benutzername);
        this.setPasswort(passwort);
        this.setVorname(vorname);
        this.setNachname(nachname);
        this.setSicherheitsfrageAntwort(sicherheitsfrageAntwort);
        this.setRolle("geschaeftsfuehrer");
        this.adresse = adresse;
        this.email = email;
        this.telefon = telefon;
    }

    // Getter und Setter
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
