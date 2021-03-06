package com.haulmont.sample.petclinic.web.controller.pet;

import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.service.PetService;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(FetchPetController.NAME)
@RequestMapping("/api/pets")
public class FetchPetController {

    public static final String NAME = "petclinic_FetchPetController";

    @Inject
    protected PetService petService;

    @GetMapping("/{petId}")
    public ResponseEntity<PetApiResponse> fetchPet(
        @PathVariable("petId") String petId
    ) {

        final Optional<Pet> possiblePet = Optional.ofNullable(
            petService.findById(petId)
        );

        if (!possiblePet.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
            PetApiResponse.of(possiblePet)
        );
    }
}