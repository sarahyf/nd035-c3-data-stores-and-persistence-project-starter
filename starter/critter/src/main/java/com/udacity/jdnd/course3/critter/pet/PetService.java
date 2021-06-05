package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    public void savePet(Pet pet) {
        petRepository.save(pet);
    }

    public Pet getPet(long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        return pet.get();
    }

    public Iterable<Pet> getAllPets() {
        Iterable<Pet> pets = petRepository.findAll();
        return pets;
    }

}
