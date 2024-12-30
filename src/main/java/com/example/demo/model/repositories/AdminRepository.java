package com.example.demo.model.repositories;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Repository für die Adminrolle

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.entities.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
}