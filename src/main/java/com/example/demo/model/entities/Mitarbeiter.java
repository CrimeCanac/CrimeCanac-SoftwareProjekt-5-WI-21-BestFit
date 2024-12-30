package com.example.demo.model.entities;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description:Entität für die Mitarbeiter-Rolle

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mitarbeiter")

public class Mitarbeiter extends User{
    @Column(name = "geburtsdatum", nullable = true)
    private Date geburtsdatum;

    @Column(name = "adresse", nullable = true)
    private String adresse;

    @Column(name = "telefon", nullable = true)
    private String telefon;

    @ManyToOne
    @JoinColumn(name = "geschaeftsfuehrer_id", referencedColumnName = "id", nullable = true)
    private Geschaeftsfuehrer geschaeftsfuehrer;

    // Konstruktoren
    public Mitarbeiter() {
        super();    
        this.setRolle("mitarbeiter");
    }

    public Mitarbeiter(String benutzername, String passwort, String vorname, String nachname, String sicherheitsfrageAntwort, Date geburtsdatum, String adresse, String telefon, Geschaeftsfuehrer geschaeftsfuehrer) {
        super();          
        this.setBenutzername(benutzername);
        this.setPasswort(passwort);
        this.setVorname(vorname);
        this.setNachname(nachname);
        this.setSicherheitsfrageAntwort(sicherheitsfrageAntwort);
        this.setRolle("mitarbeiter");
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;
        this.telefon = telefon;
        this.geschaeftsfuehrer = geschaeftsfuehrer;
    }

    // Getter und Setter
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Geschaeftsfuehrer getGeschaeftsfuehrer() {
        return geschaeftsfuehrer;
    }

    public void setGeschaeftsfuehrer(Geschaeftsfuehrer geschaeftsfuehrer) {
        this.geschaeftsfuehrer = geschaeftsfuehrer;
    }
}
