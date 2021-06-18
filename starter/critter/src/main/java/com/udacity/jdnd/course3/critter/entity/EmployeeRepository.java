package com.udacity.jdnd.course3.critter.entity;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.skills = :skills and e.daysAvailable = :dayOfWeek")
    public List<Employee> findEmployeesForService(@Param("skills") Set<EmployeeSkill> skills, 
            @Param("dayOfWeek") DayOfWeek dayOfWeek);

}
