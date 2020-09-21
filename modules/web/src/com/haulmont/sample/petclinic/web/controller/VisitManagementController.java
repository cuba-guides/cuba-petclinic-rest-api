package com.haulmont.sample.petclinic.web.controller;

import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.service.VisitService;
import com.haulmont.sample.petclinic.web.controller.model.CreateVisitApiRequest;
import com.haulmont.sample.petclinic.web.controller.model.CreateVisitApiResponse;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(VisitManagementController.NAME)
@RequestMapping("/api")
public class VisitManagementController {

    public static final String NAME = "petclinic_VisitManagementController";

    @Inject
    protected VisitService visitService;

    @PostMapping("/visits")
    public ResponseEntity<CreateVisitApiResponse> createUpcomingVisit(
        @RequestBody @Valid CreateVisitApiRequest createVisitApiRequest
    ) {

        final Visit visit = visitService.create(
            createVisitApiRequest.toVisitCreation()
        );

        return ResponseEntity.ok(
            CreateVisitApiResponse.of(visit)
        );
    }

}