package com.haulmont.sample.petclinic.web.controller.visit;

import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.service.VisitService;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(VisitController.NAME)
@RequestMapping("/api")
public class VisitController {

    public static final String NAME = "petclinic_VisitController";

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

    @GetMapping("/visits/{visitId}")
    public ResponseEntity<FetchVisitApiResponse> fetchVisit(
        @PathVariable("visitId") UUID visitId
    ) {

        final Visit visit = visitService.fetch(visitId);

        return ResponseEntity.ok(
            FetchVisitApiResponse.of(visit)
        );
    }

}