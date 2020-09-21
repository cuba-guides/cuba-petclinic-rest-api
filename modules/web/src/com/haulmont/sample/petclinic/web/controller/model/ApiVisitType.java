package com.haulmont.sample.petclinic.web.controller.model;

import com.haulmont.sample.petclinic.entity.visit.VisitType;
import java.util.stream.Stream;

public enum ApiVisitType {

    REGULAR_CHECKUP(VisitType.REGULAR_CHECKUP),
    RECHARGE(VisitType.RECHARGE),
    STATUS_CONDITION_HEALING(VisitType.STATUS_CONDITION_HEALING),
    DISEASE_TREATMENT(VisitType.DISEASE_TREATMENT);

    private final VisitType entityType;

    ApiVisitType(VisitType entityType) {
        this.entityType = entityType;
    }

    public VisitType toEntityType() {
        return entityType;
    }
}
