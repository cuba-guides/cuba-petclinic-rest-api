package com.haulmont.sample.petclinic.service;

import com.haulmont.sample.petclinic.registration.OwnerRegistration;

public interface RegistrationService {

    String NAME = "petclinic_RegistrationService";

    public boolean register(OwnerRegistration ownerRegistration);
}