package com.haulmont.sample.petclinic.service;

import java.util.UUID;

public interface TreatmentService {

    String NAME = "petclinic_TreatmentService";

    boolean start(UUID visitId);

    boolean cancel(UUID visitId);

    boolean finish(UUID visitId);
}