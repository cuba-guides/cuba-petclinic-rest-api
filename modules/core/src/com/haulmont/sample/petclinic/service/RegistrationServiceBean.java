package com.haulmont.sample.petclinic.service;

import com.haulmont.sample.petclinic.registration.OwnerRegistration;
import org.springframework.stereotype.Service;

@Service(RegistrationService.NAME)
public class RegistrationServiceBean implements RegistrationService {

    @Override
    public boolean register(OwnerRegistration ownerRegistration) {
        return false;
    }
}