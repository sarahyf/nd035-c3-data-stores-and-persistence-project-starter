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
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
    @Modifying
    @Query("update Employee e set e.daysAvailable = :daysAvailable where e.id = :employeeId")
    public void updateEmployeeSchedule(@Param("daysAvailable") Set<DayOfWeek> daysAvailable,
            @Param("employeeId") long employeeId);

    // @Modifying
    // @Query("update Employee e set e.name = :name where e.id = :employeeId")
    // public void updateEmployeeSchedule(@Param("employeeId") long employeeId, @Param("name") String name);

    @Query("select e from Employee e where e.skills = :skills and e.daysAvailable = :dayOfWeek")
    public List<Employee> findEmployeesForService(@Param("skills") Set<EmployeeSkill> skills, 
            @Param("dayOfWeek") DayOfWeek dayOfWeek);

}
