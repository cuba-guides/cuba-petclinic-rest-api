package com.haulmont.sample.petclinic.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitTreatmentStatus;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(TreatmentService.NAME)
public class TreatmentServiceBean implements TreatmentService {

    @Inject
    protected DataManager dataManager;

    @Override
    public boolean start(UUID visitId) {
        return updateVisit(
            visitId,
            visit -> updateVisitStatus(visit, VisitTreatmentStatus.IN_PROGRESS)
        );
    }

    @Override
    public boolean cancel(UUID visitId) {
        return updateVisit(
            visitId,
            visit -> updateVisitStatus(visit, VisitTreatmentStatus.UPCOMING)
        );
    }

    @Override
    public boolean finish(UUID visitId) {
        return updateVisit(
            visitId,
            visit -> updateVisitStatus(visit, VisitTreatmentStatus.DONE)
        );
    }

    private boolean updateVisitStatus(Visit visit, VisitTreatmentStatus upcoming) {
        visit.setTreatmentStatus(upcoming);
        dataManager.commit(visit);
        return true;
    }

    private boolean updateVisit(
        UUID visitId,
        Function<Visit, Boolean> operation
    ) {
        return loadVisit(visitId)
            .map(operation)
            .orElseThrow(() -> new VisitNotFoundException(visitId.toString()));
    }

    private Optional<Visit> loadVisit(UUID visitId) {
        return dataManager.load(Visit.class)
            .id(visitId)
            .optional();
    }
}