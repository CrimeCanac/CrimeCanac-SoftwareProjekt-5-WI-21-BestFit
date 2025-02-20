package com.example.demo.model.repositories;

// Written by: Ömer Yalcinkaya
// Created: 2024-12-12
// Description: Repository für die TrainingsPlan-Entität

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Trainingsplan;

@Repository
public interface TrainingsplanRepository extends JpaRepository<Trainingsplan, Long> {

    @Query("SELECT t FROM Trainingsplan t WHERE t.name LIKE %:name_str%")
    List<Trainingsplan> filterTrainingsplanByName(@Param("name_str") String nameStr);
    
}
