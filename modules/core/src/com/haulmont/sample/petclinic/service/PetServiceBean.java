package com.haulmont.sample.petclinic.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(PetService.NAME)
public class PetServiceBean implements PetService {

    @Inject
    protected DataManager dataManager;

    @Override
    public Pet findById(String petIdentificationNumber) {
        final Optional<Pet> possiblePet = dataManager.load(Pet.class)
            .query("e.identificationNumber = ?1", petIdentificationNumber)
            .view("pet-with-owner-and-type")
            .optional();

        return possiblePet
            .orElse(null);
    }
}