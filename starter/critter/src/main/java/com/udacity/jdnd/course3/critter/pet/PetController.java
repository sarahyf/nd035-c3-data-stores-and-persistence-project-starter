package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.udacity.jdnd.course3.critter.entity.Pet;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        petService.savePet(convertPetDTOToPet(petDTO));
        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        boolean loop = true;
        Iterable<Pet> pets = petService.getAllPets();
        List<PetDTO> listOfPets = new ArrayList<PetDTO>();

        while(loop) {
            if(pets.iterator().hasNext()) {
                System.out.println(convertPetToPetDTO(pets.iterator().next()).getId());
                //System.out.println("TO SEE THE PRINT " + convertPetToPetDTO(pets.iterator().next()).getName());
                listOfPets.add(convertPetToPetDTO(pets.iterator().next()));

            } else {
                loop = false;
            }
        }
        return listOfPets;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petsDTO = new ArrayList<PetDTO>();
        List<Pet> pets = petService.getPetsByOwner(ownerId);

        for(int i = 0; i < pets.size(); i++) {
            petsDTO.add(convertPetToPetDTO(pets.get(i)));
        }
        return petsDTO;
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
