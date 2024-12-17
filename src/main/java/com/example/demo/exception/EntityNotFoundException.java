package com.example.demo.exception;

// Author: Bilgesu Kara

// Created: 2024-12-10
// Last Updated: 2024-12-17
// Modified by: Bilgesu Kara
// Description: Fehler wird ausgegeben, wenn die Entität nicht gefunden wird

public class EntityNotFoundException extends RuntimeException{
    private String entityName;
    private Long entityId;

    public EntityNotFoundException(String entityName, Long entityId) {
        super(String.format("%s mit ID %d wurde nicht gefunden", entityName, entityId));
        this.entityName = entityName;
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public Long getEntityId() {
        return entityId;
    }
}
