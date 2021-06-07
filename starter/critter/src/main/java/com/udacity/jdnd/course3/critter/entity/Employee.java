package com.udacity.jdnd.course3.critter.entity;

import java.time.DayOfWeek;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private EmployeeSkill employeeSkill;
    private DayOfWeek daysAvailable;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeSkill getEmployeeSkill() {
        return this.employeeSkill;
    }

    public void setEmployeeSkill(EmployeeSkill employeeSkill) {
        this.employeeSkill = employeeSkill;
    }

    public DayOfWeek getDaysAvailable() {
        return this.daysAvailable;
    }

    public void setDaysAvailable(DayOfWeek daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

}
