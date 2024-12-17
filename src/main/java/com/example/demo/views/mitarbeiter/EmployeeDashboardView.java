package com.example.demo.views.mitarbeiter;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;


@Route(value = "mitarbeiter/dashboard", layout = EmployeeLayout.class)
public class EmployeeDashboardView extends Div{
    
    public EmployeeDashboardView() {
        setText("Willkommen auf der Aufgaben-Seite für Mitarbeiter!");
        addClassName("employee-dashboard-view");
    }
}
