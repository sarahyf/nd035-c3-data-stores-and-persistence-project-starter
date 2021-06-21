package com.udacity.jdnd.course3.critter.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    
    @Query("select p from Pet p where p.customer.id = :ownerId")
    public List<Pet> findPetsByOwner(@Param("ownerId") long ownerId);
    
}
