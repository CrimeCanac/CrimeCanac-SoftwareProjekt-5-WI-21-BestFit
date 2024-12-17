package com.example.demo.views.admin;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Admin Geschäftsführer View 

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.demo.model.entities.Geschaeftsfuehrer;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

import java.util.List;

@PageTitle("Geschäftsführerverwaltung")
@Route(value = "admin/geschaeftsfuehrer", layout = AdminLayout.class)
public class AdminGeschaeftsfuehrerView extends VerticalLayout {

    private final AdminService adminService;
    private Grid<Geschaeftsfuehrer> geschaeftsfuehrerGrid;

    @Autowired
    public AdminGeschaeftsfuehrerView(AdminService adminService) {
        this.adminService = adminService;

        setSpacing(true);
        setPadding(true);

        // Button: Neues Geschäftsführer hinzufügen
        Button addGeschaeftsfuehrerButton = new Button("Geschäftsführer hinzufügen", event -> openAddGeschaeftsfuehrerDialog());
        addGeschaeftsfuehrerButton.getStyle().set("background-color", "#28a745");
        addGeschaeftsfuehrerButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(addGeschaeftsfuehrerButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END);

        // Grid: Geschäftsführer-Liste
        geschaeftsfuehrerGrid = new Grid<>(Geschaeftsfuehrer.class, false);
        geschaeftsfuehrerGrid.addColumn(Geschaeftsfuehrer::getVorname).setHeader("Vorname");
        geschaeftsfuehrerGrid.addColumn(Geschaeftsfuehrer::getNachname).setHeader("Nachname");
        geschaeftsfuehrerGrid.addColumn(Geschaeftsfuehrer::getEmail).setHeader("Email");
        geschaeftsfuehrerGrid.addColumn(Geschaeftsfuehrer::getAdresse).setHeader("Adresse");
        geschaeftsfuehrerGrid.addColumn(Geschaeftsfuehrer::getTelefon).setHeader("Telefon");
        geschaeftsfuehrerGrid.addComponentColumn(geschaeftsfuehrer -> createActions(geschaeftsfuehrer)).setHeader("Aktionen");

        // Geschäftsführer aus der Datenbank laden
        updateGrid();

        add(headerLayout, geschaeftsfuehrerGrid);
    }

    private void updateGrid() {
        List<Geschaeftsfuehrer> geschaeftsfuehrers = adminService.alleGeschaeftsfuehrerAbrufen();
        geschaeftsfuehrerGrid.setItems(geschaeftsfuehrers);
    }

    private void openAddGeschaeftsfuehrerDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Geschäftsführer hinzufügen");
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

        // Email
        TextField emailField = new TextField("Email");
        emailField.setPlaceholder("Email eingeben");
        emailField.addClassName("dialog-input");

        // Adresse
        TextField adresseField = new TextField("Adresse");
        adresseField.setPlaceholder("Adresse eingeben");
        adresseField.addClassName("dialog-input");

        // Telefon
        TextField telefonField = new TextField("Telefon");
        telefonField.setPlaceholder("Telefonnummer eingeben");
        telefonField.addClassName("dialog-input");

        // Buttons
        Button saveButton = new Button("Speichern", event -> {
            if (vornameField.isEmpty() || nachnameField.isEmpty() || emailField.isEmpty() || adresseField.isEmpty() || telefonField.isEmpty()) {
                Notification.show("Alle Felder müssen ausgefüllt werden!", 3000, Notification.Position.MIDDLE);
            } else {
                // Geschäftsführer speichern
                Geschaeftsfuehrer neuerGeschaeftsfuehrer = new Geschaeftsfuehrer();
                neuerGeschaeftsfuehrer.setVorname(vornameField.getValue());
                neuerGeschaeftsfuehrer.setNachname(nachnameField.getValue());
                neuerGeschaeftsfuehrer.setEmail(emailField.getValue());
                neuerGeschaeftsfuehrer.setAdresse(adresseField.getValue());
                neuerGeschaeftsfuehrer.setTelefon(telefonField.getValue());

                adminService.geschaeftsfuehrerHinzufuegen(neuerGeschaeftsfuehrer);

                updateGrid();
                dialog.close();
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

        VerticalLayout dialogLayout = new VerticalLayout(vornameField, nachnameField, emailField, adresseField, telefonField, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private HorizontalLayout createActions(Geschaeftsfuehrer geschaeftsfuehrer) {
        // Bearbeiten-Button mit Icon
        Button editButton = new Button();
        editButton.getElement().setProperty("innerHTML", "<i class='fa fa-edit' style='color: #FFA500;'></i>");
        editButton.getStyle().set("background-color", "DC3545");
        editButton.getStyle().set("border", "none");
        editButton.addClickListener(event -> openEditGeschaeftsfuehrerDialog(geschaeftsfuehrer));
        editButton.getElement().setAttribute("title", "Bearbeiten");

        // Löschen-Button mit Icon
        Button deleteButton = new Button();
        deleteButton.getElement().setProperty("innerHTML", "<i class='fa fa-trash' style='color: #DC3545;'></i>");
        deleteButton.getStyle().set("background-color", "DC3545");
        deleteButton.getStyle().set("border", "none");
        deleteButton.addClickListener(event -> openDeleteConfirmationDialog(geschaeftsfuehrer));
        deleteButton.getElement().setAttribute("title", "Löschen");

        // Info-Button mit Icon
        Button infoButton = new Button();
        infoButton.getElement().setProperty("innerHTML", "<i class='fa fa-info-circle' style='color: #007BFF;'></i>");
        infoButton.getStyle().set("background-color", "DC3545");
        infoButton.getStyle().set("border", "none");
        infoButton.addClickListener(event -> openInfoDialog(geschaeftsfuehrer));
        infoButton.getElement().setAttribute("title", "Details anzeigen");

        HorizontalLayout actionsLayout = new HorizontalLayout(infoButton, editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

   
    @SuppressWarnings("deprecation")
    private void openInfoDialog(Geschaeftsfuehrer geschaeftsfuehrer) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Informationen");

        Label vornameLabel = new Label("Vorname: " + geschaeftsfuehrer.getVorname());
        Label nachnameLabel = new Label("Nachname: " + geschaeftsfuehrer.getNachname());
        Label emailLabel = new Label("Email: " + geschaeftsfuehrer.getEmail());
        Label adresseLabel = new Label("Adresse: " + geschaeftsfuehrer.getAdresse());
        Label telefonLabel = new Label("Telefon: " + geschaeftsfuehrer.getTelefon());

        Button closeButton = new Button("Schließen", event -> dialog.close());
        closeButton.getStyle().set("background-color", "#6C757D");
        closeButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(vornameLabel, nachnameLabel, emailLabel, adresseLabel, telefonLabel, closeButton);
        dialogLayout.setSpacing(true);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openEditGeschaeftsfuehrerDialog(Geschaeftsfuehrer geschaeftsfuehrer) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Geschäftsführer bearbeiten");

        // Vorname
        TextField vornameField = new TextField("Vorname");
        vornameField.setValue(geschaeftsfuehrer.getVorname());
        vornameField.addClassName("dialog-input");

        // Nachname
        TextField nachnameField = new TextField("Nachname");
        nachnameField.setValue(geschaeftsfuehrer.getNachname());
        nachnameField.addClassName("dialog-input");

        // Email
        TextField emailField = new TextField("Email");
        emailField.setValue(geschaeftsfuehrer.getEmail());
        emailField.addClassName("dialog-input");

        // Adresse
        TextField adresseField = new TextField("Adresse");
        adresseField.setValue(geschaeftsfuehrer.getAdresse());
        adresseField.addClassName("dialog-input");

        // Telefon
        TextField telefonField = new TextField("Telefon");
        telefonField.setValue(geschaeftsfuehrer.getTelefon());
        telefonField.addClassName("dialog-input");

        Button saveButton = new Button("Änderungen speichern", event -> {
            geschaeftsfuehrer.setVorname(vornameField.getValue());
            geschaeftsfuehrer.setNachname(nachnameField.getValue());
            geschaeftsfuehrer.setEmail(emailField.getValue());
            geschaeftsfuehrer.setAdresse(adresseField.getValue());
            geschaeftsfuehrer.setTelefon(telefonField.getValue());

            adminService.geschaeftsfuehrerBearbeiten(geschaeftsfuehrer.getId(), geschaeftsfuehrer);

            updateGrid();
            dialog.close();
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

        VerticalLayout dialogLayout = new VerticalLayout(vornameField, nachnameField, emailField, adresseField, telefonField, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Löschen Button Dialog
    @SuppressWarnings("deprecation")
    private void openDeleteConfirmationDialog(Geschaeftsfuehrer geschaeftsfuehrer) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Geschäftsführer löschen");

        VerticalLayout messageLayout = new VerticalLayout();
        messageLayout.add(new com.vaadin.flow.component.html.Label("Möchtest Du diesen Geschäftsführer wirklich löschen?"));

        Button confirmButton = new Button("Löschen", event -> {
            try {
                adminService.geschaeftsfuehrerLoeschen(geschaeftsfuehrer.getId());
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
