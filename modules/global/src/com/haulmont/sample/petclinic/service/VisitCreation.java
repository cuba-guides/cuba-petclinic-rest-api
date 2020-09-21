package com.haulmont.sample.petclinic.service;

import com.haulmont.sample.petclinic.entity.visit.VisitType;
import java.io.Serializable;
import java.time.LocalDateTime;

public class VisitCreation implements Serializable {

    private final LocalDateTime visitStart;
    private final LocalDateTime visitEnd;
    private final String petIdentificationNumber;
    private final VisitType type;
    private final String description;

    public VisitCreation(LocalDateTime visitStart, LocalDateTime visitEnd,
        String petIdentificationNumber, VisitType type, String description) {
        this.visitStart = visitStart;
        this.visitEnd = visitEnd;
        this.petIdentificationNumber = petIdentificationNumber;
        this.type = type;
        this.description = description;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public LocalDateTime getVisitEnd() {
        return visitEnd;
    }

    public String getPetIdentificationNumber() {
        return petIdentificationNumber;
    }

    public VisitType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

}
