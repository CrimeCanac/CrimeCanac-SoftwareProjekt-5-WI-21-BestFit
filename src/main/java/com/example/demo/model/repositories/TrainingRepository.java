package com.example.demo.model.repositories;

// Written by: Ömer Yalcinkaya
// Created: 2024-12-12
// Description: Repository für die Training-Entität

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>{
    
    @Query("SELECT t FROM Training t JOIN t.trainingsplaene tp WHERE tp.id = :trainingsplanId")
    Set<Training> findByTrainingsplanId(@Param("trainingsplanId") long trainingsplanId);
    
    

}
