//Written by Ömer Yalcinkaya

package com.example.demo.views.admin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "admin/dashboard", layout = AdminLayout.class)
public class AdminDashboardView extends Div {

    public AdminDashboardView() {
        setText("Welcome to the Admin Dashboard!");
        addClassName("admin-dashboard-view");
    }
    
}
