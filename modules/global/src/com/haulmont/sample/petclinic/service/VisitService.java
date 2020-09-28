package com.haulmont.sample.petclinic.service;

import com.haulmont.sample.petclinic.entity.visit.Visit;
import java.util.UUID;

public interface VisitService {

    String NAME = "petclinic_VisitService";

    Visit create(VisitCreation visitCreation);

    Visit fetch(UUID visitId);
}