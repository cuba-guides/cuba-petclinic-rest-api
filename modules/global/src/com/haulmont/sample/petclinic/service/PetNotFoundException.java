package com.haulmont.sample.petclinic.service;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet not found")
public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(String petIdentificationNumber) {
        super("Pet not found with Identification Number: " + petIdentificationNumber);
    }
}
