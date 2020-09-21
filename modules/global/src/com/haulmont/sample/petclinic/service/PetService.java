package com.haulmont.sample.petclinic.service;

import com.haulmont.sample.petclinic.entity.pet.Pet;

public interface PetService {

    String NAME = "petclinic_PetService";

    Pet findById(String petIdentificationNumber);
}