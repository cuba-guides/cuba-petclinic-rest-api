package com.haulmont.sample.petclinic.web.controller.visit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import java.time.LocalDateTime;
import java.util.Optional;

public class FetchVisitApiResponse {

    private final String id;
    private final String petIdentificationNumber;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDateTime visitStart;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDateTime visitEnd;

    private final ApiVisitType type;
    private final String description;


    private FetchVisitApiResponse(String id, String petIdentificationNumber,
        LocalDateTime visitStart, LocalDateTime visitEnd,
        ApiVisitType type, String description) {
        this.id = id;
        this.petIdentificationNumber = petIdentificationNumber;
        this.visitStart = visitStart;
        this.visitEnd = visitEnd;
        this.type = type;
        this.description = description;
    }

    public static Optional<FetchVisitApiResponse> of(Optional<Visit> possibleVisit) {
        return possibleVisit.map(visit ->
            new FetchVisitApiResponse(
                visit.getId().toString(),
                visit.getPet().getIdentificationNumber(),
                visit.getVisitStart(),
                visit.getVisitEnd(),
                ApiVisitType.ofEntityType(visit.getType())
                    .orElse(null),
                visit.getDescription()
            )
        );
    }

    public String getId() {
        return id;
    }

    public String getPetIdentificationNumber() {
        return petIdentificationNumber;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public LocalDateTime getVisitEnd() {
        return visitEnd;
    }

    public ApiVisitType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
