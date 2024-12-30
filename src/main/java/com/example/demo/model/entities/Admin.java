package com.example.demo.model.entities;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description:Entität für die Adminrolle

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "administrator")

public class Admin extends User {
    @Column(name = "geburtsdatum", nullable = true)
    private Date geburtsdatum;

    // Konstruktoren
    public Admin() {

        super();
        this.setRolle("administrator");
    }

    public Admin(String benutzername, String passwort, String vorname, String nachname, String sicherheitsfrageAntwort, Date geburtsdatum) {
        super();
        this.setBenutzername(benutzername);
        this.setPasswort(passwort);
        this.setVorname(vorname);
        this.setNachname(nachname);
        this.setSicherheitsfrageAntwort(sicherheitsfrageAntwort);
        this.setRolle("administrator");
        this.geburtsdatum = geburtsdatum;
    }

    // Getter und Setter
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
}
