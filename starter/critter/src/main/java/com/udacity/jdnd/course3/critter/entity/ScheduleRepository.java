package com.udacity.jdnd.course3.critter.entity;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    
    @Query("select s from Schedule s, Pet p where s.pets.petId = p.petId and p.petId = :petId")
    public List<Schedule> getScheduleForPet(@Param("petId") Long petId);

    @Query("select s from Schedule s where s.employees.employeeId = :employeeId")
    public List<Schedule> getScheduleForEmployee(@Param("employeeId") Long employeeId);

}
