package com.haulmont.sample.petclinic.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitTreatmentStatus;
import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(VisitService.NAME)
public class VisitServiceBean implements VisitService {

    @Inject
    protected DataManager dataManager;
    @Inject
    protected UserSessionSource userSessionSource;

    @Override
    public Visit create(VisitCreation visitCreation) {

        final Optional<Pet> pet = loadPetById(
            visitCreation.getPetIdentificationNumber()
        );

        final Visit visit = pet
            .map(it -> mapToVisit(visitCreation, it))
            .orElseThrow(
                () -> new PetNotFoundException(visitCreation.getPetIdentificationNumber())
            );

        return dataManager.commit(visit);

    }

    @Override
    public Visit fetch(UUID visitId) {
        final Optional<Visit> possibleVisit = dataManager.load(Visit.class)
            .id(visitId)
            .optional();

        return possibleVisit
            .orElseThrow(() -> new VisitNotFoundException(visitId.toString()));
    }


    private Visit mapToVisit(VisitCreation visitCreation, Pet pet) {
        final Visit visit = dataManager.create(Visit.class);
        visit.setType(visitCreation.getType());
        visit.setPet(pet);
        visit.setAssignedNurse(currentUser());
        visit.setVisitStart(visitCreation.getVisitStart());
        visit.setVisitEnd(visitCreation.getVisitEnd());
        visit.setDescription(visitCreation.getDescription());
        visit.setTreatmentStatus(VisitTreatmentStatus.UPCOMING);
        return visit;
    }

    private User currentUser() {
        return userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
    }


    private Optional<Pet> loadPetById(String petIdentificationNumber) {
        return dataManager.load(Pet.class)
            .query("e.identificationNumber = ?1", petIdentificationNumber)
            .view("pet-with-owner-and-type")
            .optional();
    }
}