package com.example.demo.model.repositories;

// Written by: Ömer Yalcinkaya
// Created: 2024-12-12
// Description: Repository für die Uebung.


import com.example.demo.model.enums.Muskelgruppe;
import com.example.demo.model.entities.Uebung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UebungRepository extends JpaRepository<Uebung, Long> {
    List<Uebung> findByMuskelgruppe(Muskelgruppe muskelgruppe);
}
