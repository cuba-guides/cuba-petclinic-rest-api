package com.haulmont.sample.petclinic.web.controller.treatment;

import com.haulmont.sample.petclinic.service.TreatmentService;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(TreatmentController.NAME)
@RequestMapping("/api")
public class TreatmentController {

    public static final String NAME = "petclinic_TreatmentController";

    @Inject
    protected TreatmentService treatmentService;

    @PostMapping("/treatments/{visitId}")
    public ResponseEntity<Void> startTreatment(
        @PathVariable("visitId") UUID visitId
    ) {
        treatmentService.start(visitId);
        return noContent();
    }

    @PatchMapping("/treatments/{visitId}")
    public ResponseEntity<Void> finishTreatment(
        @PathVariable("visitId") UUID visitId,
        @RequestBody @Valid FinishTreatmentApiRequest request
    ) {

        if (request.isCancelled()) {
            treatmentService.cancel(visitId);
        }
        else {
            treatmentService.finish(visitId);
        }

        return noContent();
    }

    private ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }
}