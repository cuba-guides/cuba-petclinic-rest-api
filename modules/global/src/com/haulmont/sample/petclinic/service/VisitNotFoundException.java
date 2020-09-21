package com.haulmont.sample.petclinic.service;

import com.haulmont.cuba.core.global.SupportedByClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SupportedByClient
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Visit not found")
public class VisitNotFoundException extends RuntimeException {

    public VisitNotFoundException(String id) {
        super("Visit not found with Identification Number: " + id);
    }
}
