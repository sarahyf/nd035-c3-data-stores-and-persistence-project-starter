package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Transactional
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPet(long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        return pet.get();
    }

    public Iterable<Pet> getAllPets() {
        Iterable<Pet> pets = petRepository.findAll();
        return pets;
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.findPetsByOwner(ownerId);
    }

}
