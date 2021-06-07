package com.udacity.jdnd.course3.critter.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
// @Transactional
public interface PetRepository extends CrudRepository<Pet, Long> {
    
    //public List<Pet> findPetsByOwner(long ownerId);

    @Query("select p from Pet p where p.name = :name")
    public List<Pet> findPetsByName(@Param("name") String name);
    
}
