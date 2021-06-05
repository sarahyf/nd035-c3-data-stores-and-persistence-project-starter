package com.udacity.jdnd.course3.critter.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    
}
