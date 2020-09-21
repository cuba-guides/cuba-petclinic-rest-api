package com.haulmont.sample.petclinic.web.controller.model;

import com.haulmont.sample.petclinic.entity.visit.VisitType;
import com.haulmont.sample.petclinic.service.VisitCreation;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class CreateVisitApiRequest {

    @NotNull
    private LocalDateTime visitStart;
    @NotNull
    private LocalDateTime visitEnd;
    @NotNull
    private String petIdentificationNumber;
    @NotNull
    private ApiVisitType type;

    private String description;

    public CreateVisitApiRequest() { }

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
        return type.toEntityType();
    }

    public String getDescription() {
        return description;
    }

    public VisitCreation toVisitCreation() {
        return new VisitCreation(
            visitStart,
            visitEnd,
            petIdentificationNumber,
            type.toEntityType(),
            description
        );
    }
}
