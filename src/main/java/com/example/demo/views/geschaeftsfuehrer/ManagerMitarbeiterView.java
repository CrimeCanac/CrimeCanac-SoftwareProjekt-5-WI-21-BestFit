package com.example.demo.views.geschaeftsfuehrer;
// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Geschäftsführer Mitarbeiter View 

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.demo.model.entities.Mitarbeiter;
import com.example.demo.service.GeschaeftsfuehrerService;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@PageTitle("Mitarbeiterverwaltung")
@Route(value = "manager/mitarbeiter", layout = ManagerLayout.class)

public class ManagerMitarbeiterView extends VerticalLayout {
    
    private final GeschaeftsfuehrerService geschaeftsfuehrerService;
    private final Grid<Mitarbeiter> mitarbeiterGrid;

    @Autowired
    public ManagerMitarbeiterView(GeschaeftsfuehrerService geschaeftsfuehrerService) {
        this.geschaeftsfuehrerService = geschaeftsfuehrerService;
        setSpacing(true);
        setPadding(true);

        // Button: Neuer Mitarbeiter hinzufügen
        Button addMitarbeiterButton = new Button("Mitarbeiter hinzufügen", event -> openAddMitarbeiterDialog());
        addMitarbeiterButton.getStyle().set("background-color", "#28a745");
        addMitarbeiterButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(addMitarbeiterButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END);

        // Grid: Mitarbeiterliste
        mitarbeiterGrid = new Grid<>(Mitarbeiter.class, false);
        mitarbeiterGrid.addColumn(Mitarbeiter::getId).setHeader("ID").setWidth("100px").setFlexGrow(0);
        mitarbeiterGrid.addColumn(Mitarbeiter::getVorname).setHeader("Vorname");
        mitarbeiterGrid.addColumn(Mitarbeiter::getNachname).setHeader("Nachname");
        mitarbeiterGrid.addColumn(Mitarbeiter::getAdresse).setHeader("Adresse");
        mitarbeiterGrid.addColumn(Mitarbeiter::getTelefon).setHeader("Telefon");
        mitarbeiterGrid.addColumn(Mitarbeiter::getGeburtsdatum).setHeader("Geburtsdatum");
        mitarbeiterGrid.addComponentColumn(mitarbeiter -> createActions(mitarbeiter)).setHeader("Aktionen");

        // Mitarbeiter aus der Datenbank laden
        updateGrid();

        add(headerLayout, mitarbeiterGrid);
    }

    private void updateGrid() {
        List<Mitarbeiter> mitarbeiter = geschaeftsfuehrerService.alleMitarbeiterAbrufen();
        mitarbeiterGrid.setItems(mitarbeiter);
    }

    private void openAddMitarbeiterDialog() {
    Dialog dialog = new Dialog();
    dialog.setHeaderTitle("Mitarbeiter hinzufügen");
    dialog.setWidth("400px");
    dialog.setHeight("auto");
    dialog.setResizable(true);

    // Vorname
    TextField vornameField = new TextField("Vorname");
    vornameField.setPlaceholder("Vorname eingeben");
    vornameField.addClassName("dialog-input");

    // Nachname
    TextField nachnameField = new TextField("Nachname");
    nachnameField.setPlaceholder("Nachname eingeben");
    nachnameField.addClassName("dialog-input");

    // Geburtsdatum
    TextField geburtsdatumField = new TextField("Geburtsdatum");
    geburtsdatumField.setPlaceholder("DD.MM.YYYY");
    geburtsdatumField.addClassName("dialog-input");

    // Adresse
    TextField adresseField = new TextField("Adresse");
    adresseField.setPlaceholder("Adresse eingeben");
    adresseField.addClassName("dialog-input");

    // Telefon
    TextField telefonField = new TextField("Telefon");
    telefonField.setPlaceholder("Telefonnummer eingeben");
    telefonField.addClassName("dialog-input");

    Button saveButton = new Button("Speichern", event -> {
        if (vornameField.isEmpty() || nachnameField.isEmpty() || geburtsdatumField.isEmpty() || adresseField.isEmpty() || telefonField.isEmpty()) {
            Notification.show("Alle Felder müssen ausgefüllt werden!", 3000, Notification.Position.MIDDLE);
        } else {
            String geburtsdatumString = geburtsdatumField.getValue();
            try {
                Date geburtsdatumDate = new SimpleDateFormat("dd.MM.yyyy").parse(geburtsdatumString);
                
                Mitarbeiter neuerMitarbeiter = new Mitarbeiter();
                neuerMitarbeiter.setVorname(vornameField.getValue());
                neuerMitarbeiter.setNachname(nachnameField.getValue());
                neuerMitarbeiter.setGeburtsdatum(geburtsdatumDate);
                neuerMitarbeiter.setAdresse(adresseField.getValue());
                neuerMitarbeiter.setTelefon(telefonField.getValue());

                geschaeftsfuehrerService.mitarbeiterHinzufuegen(neuerMitarbeiter);

                updateGrid();
                dialog.close();
            } catch (ParseException e) {
                Notification.show("Fehler: Bitte geben Sie das Geburtsdatum im Format DD.MM.YYYY ein.");
            }
        }
    });

    saveButton.getStyle().set("background-color", "#FFA500");
    saveButton.getStyle().set("color", "white");

    Button cancelButton = new Button("Abbrechen", event -> dialog.close());
    cancelButton.getStyle().set("color", "#007BFF");
    
    HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
    buttonLayout.setSpacing(true);
    buttonLayout.setWidthFull();
    buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);    
    
    VerticalLayout dialogLayout = new VerticalLayout(vornameField, nachnameField, geburtsdatumField, adresseField, telefonField, buttonLayout);
    dialog.add(dialogLayout);
    dialog.open();
}

    private HorizontalLayout createActions(Mitarbeiter mitarbeiter) {

        // Bearbeiten-Button mit Icon
        Button editButton = new Button();
        editButton.getElement().setProperty("innerHTML", "<i class='fa fa-edit' style='color: #FFA500;'></i>");
        editButton.getStyle().set("background-color", "DC3545");
        editButton.getStyle().set("border", "none");
        editButton.addClickListener(event -> openEditMitarbeiterDialog(mitarbeiter));
        // Tooltip über das title-Attribut
        editButton.getElement().setAttribute("title", "Bearbeiten");

        // Löschen-Button mit Icon
        Button deleteButton = new Button();
        deleteButton.getElement().setProperty("innerHTML", "<i class='fa fa-trash' style='color: #DC3545;'></i>");
        deleteButton.getStyle().set("background-color", "DC3545");
        deleteButton.getStyle().set("border", "none");
        deleteButton.addClickListener(event -> openDeleteConfirmationDialog(mitarbeiter));

        // Tooltip über das title-Attribut
        deleteButton.getElement().setAttribute("title", "Löschen");

        // Info-Button mit Icon
        Button infoButton = new Button();
        infoButton.getElement().setProperty("innerHTML", "<i class='fa fa-info-circle' style='color: #007BFF;'></i>");
        infoButton.getStyle().set("background-color", "DC3545");
        infoButton.getStyle().set("border", "none");
        infoButton.addClickListener(event -> openInfoDialog(mitarbeiter));

        // Tooltip über das title-Attribut
        infoButton.getElement().setAttribute("title", "Details anzeigen");

        HorizontalLayout actionsLayout = new HorizontalLayout(infoButton, editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

    @SuppressWarnings("deprecation")
    private void openInfoDialog(Mitarbeiter mitarbeiter) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Informationen");

        // Vorname anzeigen
        Label vornameLabel = new Label("Vorname: " + mitarbeiter.getVorname());

        // Nachname anzeigen
        Label nachnameLabel = new Label("Nachname: " + mitarbeiter.getNachname());

        // Adresse anzeigen
        Label adresseLabel = new Label("Adresse: " + mitarbeiter.getAdresse());

        // Telefon anzeigen
        Label telefonLabel = new Label("Telefon: " + mitarbeiter.getTelefon());

        // Geburtsdatum anzeigen
        Label geburtsdatumLabel = new Label("Geburtsdatum: " + mitarbeiter.getGeburtsdatum());

        Button closeButton = new Button("Schließen", event -> dialog.close());
        closeButton.getStyle().set("background-color", "#6C757D");
        closeButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(vornameLabel, nachnameLabel, adresseLabel, telefonLabel, geburtsdatumLabel, closeButton);
        dialogLayout.setSpacing(true);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openEditMitarbeiterDialog(Mitarbeiter mitarbeiter) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Mitarbeiter bearbeiten");
    
        // Vorname
        TextField vornameField = new TextField("Vorname");
        vornameField.setValue(mitarbeiter.getVorname());
        vornameField.addClassName("dialog-input");
    
        // Nachname
        TextField nachnameField = new TextField("Nachname");
        nachnameField.setValue(mitarbeiter.getNachname());
        nachnameField.addClassName("dialog-input");
    
        // Geburtsdatum
        TextField geburtsdatumField = new TextField("Geburtsdatum");
        geburtsdatumField.setValue(new SimpleDateFormat("dd.MM.yyyy").format(mitarbeiter.getGeburtsdatum()));
        geburtsdatumField.addClassName("dialog-input");
    
        // Adresse
        TextField adresseField = new TextField("Adresse");
        adresseField.setValue(mitarbeiter.getAdresse());
        adresseField.addClassName("dialog-input");
    
        // Telefon
        TextField telefonField = new TextField("Telefon");
        telefonField.setValue(mitarbeiter.getTelefon());
        telefonField.addClassName("dialog-input");
    
        Button saveButton = new Button("Änderungen speichern", event -> {
            
            String geburtsdatumString = geburtsdatumField.getValue();
            try {
                Date geburtsdatumDate = new SimpleDateFormat("dd.MM.yyyy").parse(geburtsdatumString);
                
                mitarbeiter.setVorname(vornameField.getValue());
                mitarbeiter.setNachname(nachnameField.getValue());
                mitarbeiter.setGeburtsdatum(geburtsdatumDate);
                mitarbeiter.setAdresse(adresseField.getValue());
                mitarbeiter.setTelefon(telefonField.getValue());
    
                geschaeftsfuehrerService.mitarbeiterBearbeiten(mitarbeiter.getId(), mitarbeiter);
    
                updateGrid();
                dialog.close();
            } catch (ParseException e) {
                Notification.show("Fehler: Bitte geben Sie das Geburtsdatum im Format DD.MM.YYYY ein.");
            }
        });
    
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        Button cancelButton = new Button("Abbrechen", event -> dialog.close());
        cancelButton.getStyle().set("color", "#007BFF");

        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonLayout.setSpacing(true);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);        
        
        VerticalLayout dialogLayout = new VerticalLayout(vornameField, nachnameField, geburtsdatumField, adresseField, telefonField, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Löschen Button Dialog
    @SuppressWarnings("deprecation")
    private void openDeleteConfirmationDialog(Mitarbeiter mitarbeiter) {
        // Dialog erstellen
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Mitarbeiter löschen");

        // Nachricht
        VerticalLayout messageLayout = new VerticalLayout();
        messageLayout.add(new com.vaadin.flow.component.html.Label("Möchtest Du diesen Mitarbeiter wirklich löschen?"));

        // Bestätigungs-Button
        Button confirmButton = new Button("Löschen", event -> {
            try {
                geschaeftsfuehrerService.mitarbeiterLoeschen(mitarbeiter.getId()); // Mitarbeiter löschen
                updateGrid(); // Grid aktualisieren
            } catch (RuntimeException ex) {
                Notification.show(ex.getMessage(), 3500, Notification.Position.MIDDLE);
            }
            dialog.close(); // Dialog schließen
        });
        confirmButton.getStyle().set("background-color", "#FF0000"); // Roter Hintergrund
        confirmButton.getStyle().set("color", "white");

        // Abbrechen-Button
        Button cancelButton = new Button("Abbrechen", event -> dialog.close());
        cancelButton.getStyle().set("color", "#007BFF"); // Blau für Abbrechen

        // Layout für Buttons
        HorizontalLayout buttonLayout = new HorizontalLayout(confirmButton, cancelButton);
        buttonLayout.setSpacing(true);

        // Dialog zusammensetzen
        VerticalLayout dialogLayout = new VerticalLayout(messageLayout, buttonLayout);
        dialogLayout.setSpacing(true);
        dialog.add(dialogLayout);

        dialog.open(); // Dialog öffnen
    }
}
