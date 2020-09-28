package com.haulmont.sample.petclinic.web.controller.pet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import java.time.LocalDate;
import java.util.Optional;

public class PetApiResponse {

    private final String identificationNumber;
    private final String name;
    private final String type;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDate birthDate;
    private final String owner;

    public PetApiResponse(
        String identificationNumber,
        String name,
        String type,
        LocalDate birthDate,
        String owner
    ) {
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.owner = owner;
    }

    public static Optional<PetApiResponse> of(Optional<Pet> possiblePet) {
        return possiblePet.map(pet -> new PetApiResponse(
            pet.getIdentificationNumber(),
            pet.getName(),
            pet.getType().getName(),
            pet.getBirthDate(),
            pet.getOwner().getName())
        );
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getOwner() {
        return owner;
    }
}
