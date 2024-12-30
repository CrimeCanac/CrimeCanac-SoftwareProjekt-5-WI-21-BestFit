package com.example.demo.views.geschaeftsfuehrer;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "manager/dashboard", layout = ManagerLayout.class)
public class ManagerDashboardView extends Div {

    public ManagerDashboardView() {
        setText("Willkommen im Geschäftsführer Dashboard");
        addClassName("manager-dashboard-view");
    }
}
