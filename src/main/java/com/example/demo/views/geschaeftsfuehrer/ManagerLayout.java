package com.example.demo.views.geschaeftsfuehrer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Anchor;

public class ManagerLayout extends AppLayout{
    
    private boolean isDrawerOpen = false;

    public ManagerLayout() {
        // CSS- und JS-Dateien einbinden
        UI.getCurrent().getPage().addStyleSheet("/css/bootstrap.min.css");
        UI.getCurrent().getPage().addStyleSheet("/css/style.css");
        UI.getCurrent().getPage().addJavaScript("/js/main.js");

        // Header
        Div header = createHeader();
        addToNavbar(header);

        // Drawer (Seitenmenü)
        Div drawer = createDrawer();
        addToDrawer(drawer);
    }

    private Div createHeader() {
        Div header = new Div();
        header.addClassName("header");

        Button hamburgerMenuButton = new Button(new Icon(VaadinIcon.MENU));
        hamburgerMenuButton.addClassName("hamburger-icon");
        hamburgerMenuButton.addClickListener(event -> toggleDrawer());

        Image logo = new Image("/images/bestfit.png", "Admin Logo");
        logo.addClassName("header-logo");
        
        Div managerViewLabel = new Div();
        managerViewLabel.setText("Geschäftsführer-Ansicht");
        managerViewLabel.addClassName("admin-view-label"); // Optional: CSS-Klasse für Styling


        Button logoutButton = new Button("Logout");
        logoutButton.addClassName("logout-button");
        logoutButton.addClickListener(event -> UI.getCurrent().navigate("login"));
        logoutButton.getStyle().set("background-color", "#FF0000"); // Roter Hintergrund
        logoutButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(hamburgerMenuButton, logo, managerViewLabel, logoutButton);
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        headerLayout.setWidthFull();

        header.add(headerLayout);
        return header;
    }

    private Div createDrawer() {
        Div drawer = new Div();
        drawer.addClassName("drawer");

        VerticalLayout menu = new VerticalLayout();
        menu.add(createDrawerMenuItem("Profile", "/manager/profile"));
        menu.add(createDrawerMenuItem("Mitglieder", "/manager/mitglieder"));
        menu.add(createDrawerMenuItem("Mitarbeiter", "/manager/mitarbeiter"));
        menu.add(createDrawerMenuItem("Übungen", "/manager/uebungen"));
        menu.add(createDrawerMenuItem("Geräte", "/manager/geraete"));
        menu.addClassName("drawer-menu");

        drawer.add(menu);
        return drawer;
    }

    private Anchor createDrawerMenuItem(String text, String route) {
        Anchor menuItem = new Anchor(route, text);
        menuItem.addClassName("drawer-menu-item");
        return menuItem;
    }

    private void toggleDrawer() {
        if (isDrawerOpen) {
            UI.getCurrent().getPage().executeJs("document.querySelector('.drawer').style.left = '-250px'");
        } else {
            UI.getCurrent().getPage().executeJs("document.querySelector('.drawer').style.left = '0'");
        }
        isDrawerOpen = !isDrawerOpen;
    }
}