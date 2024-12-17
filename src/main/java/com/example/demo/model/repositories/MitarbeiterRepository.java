package com.example.demo.model.repositories;
// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Repository für die Mitarbeiter Rolle

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.entities.Mitarbeiter;

@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {
    
}
