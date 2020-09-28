package com.haulmont.sample.petclinic.web.controller.visit;

import com.haulmont.sample.petclinic.entity.visit.Visit;

public class CreateVisitApiResponse {

    private final String id;

    private CreateVisitApiResponse(String id) {
        this.id = id;
    }

    public static CreateVisitApiResponse of(Visit visit) {
        return new CreateVisitApiResponse(visit.getId().toString());
    }

    public String getId() {
        return id;
    }
}
