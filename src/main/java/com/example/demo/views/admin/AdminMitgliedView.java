package com.example.demo.views.admin;
// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Admin Mitglied View 

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.demo.model.entities.Mitglied;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@PageTitle("Mitgliederverwaltung")
@Route(value = "admin/mitglieder", layout = AdminLayout.class)
public class AdminMitgliedView extends VerticalLayout {

    private final AdminService adminService;
    private Grid<Mitglied> mitgliedGrid;

    @Autowired
    public AdminMitgliedView(AdminService adminService) {
        this.adminService = adminService;

        setSpacing(true);
        setPadding(true);

        // Button: Neues Mitglied hinzufügen
        Button addMitgliedButton = new Button("Mitglied hinzufügen", event -> openAddMitgliedDialog());
        addMitgliedButton.getStyle().set("background-color", "#28a745");
        addMitgliedButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(addMitgliedButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END);

        // Grid: Mitgliederliste
        mitgliedGrid = new Grid<>(Mitglied.class, false);
        mitgliedGrid.addColumn(Mitglied::getId).setHeader("ID").setWidth("100px").setFlexGrow(0);
        mitgliedGrid.addColumn(Mitglied::getVorname).setHeader("Vorname");
        mitgliedGrid.addColumn(Mitglied::getNachname).setHeader("Nachname");
        mitgliedGrid.addColumn(Mitglied::getAdresse).setHeader("Adresse");
        mitgliedGrid.addColumn(Mitglied::getTelefon).setHeader("Telefonnummer");
        mitgliedGrid.addColumn(Mitglied::getGeburtsdatum).setHeader("Geburtsdatum");
        mitgliedGrid.addColumn(Mitglied::getGewichtKg).setHeader("Gewicht (kg)");
        mitgliedGrid.addColumn(Mitglied::getGroesseCm).setHeader("Größe (cm)");
        mitgliedGrid.addColumn(mitglied -> mitglied.isIstPremium() ? "Ja" : "Nein")
                    .setHeader("Premium");
        mitgliedGrid.addComponentColumn(mitglied -> createActions(mitglied)).setHeader("Aktionen");

        // Mitglieder aus der Datenbank laden
        updateGrid();

        add(headerLayout, mitgliedGrid);
    }

    private void updateGrid() {
        List<Mitglied> mitglieder = adminService.alleMitgliederAbrufen();
        mitgliedGrid.setItems(mitglieder);
    }

    private void openAddMitgliedDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Mitglied hinzufügen");
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
        
        // Gewicht (kg)
        TextField gewichtKgField = new TextField("Gewicht (kg)");
        gewichtKgField.setPlaceholder("Gewicht in kg");
        gewichtKgField.addClassName("dialog-input");
        
        // Größe (cm)
        TextField groesseCmField = new TextField("Größe (cm)");
        groesseCmField.setPlaceholder("Größe in cm");
        groesseCmField.addClassName("dialog-input");
        
        Checkbox istPremiumCheckbox = new Checkbox("Premium Mitglied");

        // Buttons
        Button saveButton = new Button("Speichern", event -> {
            if (vornameField.isEmpty() || nachnameField.isEmpty()) {
                Notification.show("Alle Felder müssen ausgefüllt werden!", 3000, Notification.Position.MIDDLE);
            } else {
                String geburtsdatumString = geburtsdatumField.getValue();
                try {
                    Date geburtsdatumDate = new SimpleDateFormat("dd.MM.yyyy").parse(geburtsdatumString);
                    
                    Mitglied neuesMitglied = new Mitglied();
                    neuesMitglied.setVorname(vornameField.getValue());
                    neuesMitglied.setNachname(nachnameField.getValue());
                    neuesMitglied.setGeburtsdatum(geburtsdatumDate);
                    neuesMitglied.setAdresse(adresseField.getValue());
                    neuesMitglied.setTelefon(telefonField.getValue());
                    neuesMitglied.setIstPremium(istPremiumCheckbox.getValue());
                    neuesMitglied.setGewichtKg(Double.parseDouble(gewichtKgField.getValue()));
                    neuesMitglied.setGroesseCm(Double.parseDouble(groesseCmField.getValue()));
                    adminService.mitgliedHinzufuegen(neuesMitglied);
    
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

        // HorizontalLayout für die Buttons
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonLayout.setSpacing(true);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout dialogLayout = new VerticalLayout(vornameField, nachnameField, geburtsdatumField, adresseField, telefonField, istPremiumCheckbox, gewichtKgField,groesseCmField, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private HorizontalLayout createActions(Mitglied mitglied) {

        // Bearbeiten-Button mit Icon
        Button editButton = new Button();
        editButton.getElement().setProperty("innerHTML", "<i class='fa fa-edit' style='color: #FFA500;'></i>");
        editButton.getStyle().set("background-color", "DC3545");
        editButton.getStyle().set("border", "none");
        editButton.addClickListener(event -> openEditMitgliedDialog(mitglied));
        // Tooltip über das title-Attribut
        editButton.getElement().setAttribute("title", "Bearbeiten");

        // Löschen-Button mit Icon
        Button deleteButton = new Button();
        deleteButton.getElement().setProperty("innerHTML", "<i class='fa fa-trash' style='color: #DC3545;'></i>");
        deleteButton.getStyle().set("background-color", "DC3545");
        deleteButton.getStyle().set("border", "none");
        deleteButton.addClickListener(event -> openDeleteConfirmationDialog(mitglied));
        // Tooltip über das title-Attribut
        deleteButton.getElement().setAttribute("title", "Löschen");

        // Info-Button mit Icon
        Button infoButton = new Button();
        infoButton.getElement().setProperty("innerHTML", "<i class='fa fa-info-circle' style='color: #007BFF;'></i>");
        infoButton.getStyle().set("background-color", "DC3545");
        infoButton.getStyle().set("border", "none");
        infoButton.addClickListener(event -> openInfoDialog(mitglied));
        // Tooltip über das title-Attribut
        infoButton.getElement().setAttribute("title", "Details anzeigen");

        HorizontalLayout actionsLayout = new HorizontalLayout(infoButton, editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }
    @SuppressWarnings("deprecation")
    private void openInfoDialog(Mitglied mitglied) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Informationen");

        // Vorname anzeigen
        Label vornameLabel = new Label("Vorname: " + mitglied.getVorname());

        // Nachname anzeigen
        Label nachnameLabel = new Label("Nachname: " + mitglied.getNachname());

        // Adresse anzeigen
        Label adresseLabel = new Label("Adresse: " + mitglied.getAdresse());

        // Telefon anzeigen
        Label telefonLabel = new Label("Telefon: " + mitglied.getTelefon());

        // Geburtsdatum anzeigen
        Label geburtsdatumLabel = new Label("Geburtsdatum: " + mitglied.getGeburtsdatum());

        // Premium-Status anzeigen
        Label premiumLabel = new Label("Premium: " + (mitglied.isIstPremium() ? "Ja" : "Nein"));

        //Gewicht anzeigen
        Label gewichtLabel = new Label("Gewicht: " + (mitglied.getGewichtKg()));

        //Groesse anzeigen
        Label groesseLabel = new Label("Größe: " + (mitglied.getGroesseCm()));


        Button closeButton = new Button("Schließen", event -> dialog.close());
        closeButton.getStyle().set("background-color", "#6C757D");
        closeButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(vornameLabel, nachnameLabel, adresseLabel, telefonLabel, geburtsdatumLabel, premiumLabel, gewichtLabel,groesseLabel, closeButton);
        dialogLayout.setSpacing(true);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openEditMitgliedDialog(Mitglied mitglied) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Mitglied bearbeiten");

        // Vorname
        TextField vornameField = new TextField("Vorname");
        vornameField.setValue(mitglied.getVorname());
        vornameField.addClassName("dialog-input");

        // Nachname
        TextField nachnameField = new TextField("Nachname");
        nachnameField.setValue(mitglied.getNachname());
        nachnameField.addClassName("dialog-input");

        // Geburtsdatum
        TextField geburtsdatumField = new TextField("Geburtsdatum");
        geburtsdatumField.setValue(new SimpleDateFormat("dd.MM.yyyy").format(mitglied.getGeburtsdatum()));
        geburtsdatumField.addClassName("dialog-input");

        // Adresse
        TextField adresseField = new TextField("Adresse");
        adresseField.setValue(mitglied.getAdresse());
        adresseField.addClassName("dialog-input");
    
        // Telefon
        TextField telefonField = new TextField("Telefon");
        telefonField.setValue(mitglied.getTelefon());
        telefonField.addClassName("dialog-input");

        //Gewicht
        TextField gewichtField = new TextField("Gewicht");
        gewichtField.setValue(String.valueOf(mitglied.getGewichtKg()));
        gewichtField.addClassName("dialog-input");

        //Groesse
        TextField groesseField = new TextField("Größe");
        groesseField.setValue(String.valueOf(mitglied.getGroesseCm()));
        groesseField.addClassName("dialog-input");

        Checkbox istPremiumCheckbox = new Checkbox("Premium Mitglied");
        istPremiumCheckbox.setValue(mitglied.isIstPremium());

        Button saveButton = new Button("Änderungen speichern", event -> {
            String geburtsdatumString = geburtsdatumField.getValue();
            try {
                Date geburtsdatumDate = new SimpleDateFormat("dd.MM.yyyy").parse(geburtsdatumString);
                
                mitglied.setVorname(vornameField.getValue());
                mitglied.setNachname(nachnameField.getValue());
                mitglied.setGeburtsdatum(geburtsdatumDate);
                mitglied.setAdresse(adresseField.getValue());
                mitglied.setTelefon(telefonField.getValue());
                mitglied.setIstPremium(istPremiumCheckbox.getValue());
                mitglied.setGewichtKg(Double.parseDouble(gewichtField.getValue()));
                mitglied.setGroesseCm(Double.parseDouble(groesseField.getValue()));

                adminService.mitgliedBearbeiten(mitglied.getId(), mitglied);
    
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

        VerticalLayout dialogLayout = new VerticalLayout(vornameField, nachnameField, geburtsdatumField, adresseField, telefonField, gewichtField, groesseField, istPremiumCheckbox, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Löschen Button Dialog
    @SuppressWarnings("deprecation")
    private void openDeleteConfirmationDialog(Mitglied mitglied) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Mitglied löschen");

        VerticalLayout messageLayout = new VerticalLayout();
        messageLayout.add(new Label("Möchtest Du dieses Mitglied wirklich löschen?"));

        Button confirmButton = new Button("Löschen", event -> {
            try {
                adminService.mitgliedLoeschen(mitglied.getId());
                updateGrid();
            } catch (RuntimeException ex) {
                Notification.show(ex.getMessage(), 3500, Notification.Position.MIDDLE);
            }
            dialog.close();
        });
        confirmButton.getStyle().set("background-color", "#FF0000");
        confirmButton.getStyle().set("color", "white");

        Button cancelButton = new Button("Abbrechen", event -> dialog.close());
        cancelButton.getStyle().set("color", "#007BFF");

        HorizontalLayout buttonLayout = new HorizontalLayout(confirmButton, cancelButton);
        buttonLayout.setSpacing(true);

        VerticalLayout dialogLayout = new VerticalLayout(messageLayout, buttonLayout);
        dialogLayout.setSpacing(true);
        dialog.add(dialogLayout);

        dialog.open();
    }
}
