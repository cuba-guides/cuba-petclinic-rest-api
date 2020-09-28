package com.haulmont.sample.petclinic.web.controller.treatment;

import javax.validation.constraints.NotNull;

public class FinishTreatmentApiRequest {

    @NotNull
    TreatmentOutcome outcome;

    public FinishTreatmentApiRequest() { }

    public boolean isCancelled() {
        return outcome.equals(TreatmentOutcome.CANCELLED);
    }

    public TreatmentOutcome getOutcome() {
        return outcome;
    }
}
