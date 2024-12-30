package com.example.demo.model.entities;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description:Entität für die Mitglied Rolle

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mitglied")
public class Mitglied extends User {
    @Column(name = "geburtsdatum", nullable = true)
    private Date geburtsdatum;

    @Column(name = "adresse", nullable = true)
    private String adresse;

    @Column(name = "telefon", nullable = true)
    private String telefon;

    @Column(name = "ist_premium", nullable = false)
    private boolean istPremium = false;

    @Column(name = "gewicht_kg", nullable = true)
    private double gewichtKg;

    @Column(name = "groesse_cm", nullable = true)
    private double groesseCm;

    // Konstruktoren
    public Mitglied() {
        super();
        this.setRolle("mitglied");  
    }

    public Mitglied(String benutzername, String passwort, String vorname, String nachname, String sicherheitsfrageAntwort, Date geburtsdatum, String adresse, String telefon, boolean istPremium, double gewichtKg, double groesseCm) {
        super();
        this.setBenutzername(benutzername);
        this.setPasswort(passwort);
        this.setVorname(vorname);
        this.setNachname(nachname);
        this.setSicherheitsfrageAntwort(sicherheitsfrageAntwort);
        this.setRolle("mitglied");
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;
        this.telefon = telefon;
        this.istPremium = istPremium;
        this.gewichtKg = gewichtKg;
        this.groesseCm = groesseCm;
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

    public boolean isIstPremium() {
        return istPremium;
    }

    public void setIstPremium(boolean istPremium) {
        this.istPremium = istPremium;
    }

    public Double getGewichtKg() {
        return gewichtKg;
    }

    public void setGewichtKg(double gewichtKg) {
        this.gewichtKg = gewichtKg;
    }

    public Double getGroesseCm() {
        return groesseCm;
    }

    public void setGroesseCm(double groesseCm) {
        this.groesseCm = groesseCm;
    }
}
