package com.udacity.jdnd.course3.critter.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany
    private List<Employee> employees;
    @OneToMany
    private List<Pet> pets;
    private LocalDate date;
    private EmployeeSkill employeeSkill;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EmployeeSkill getEmployeeSkill() {
        return this.employeeSkill;
    }

    public void setEmployeeSkill(EmployeeSkill employeeSkill) {
        this.employeeSkill = employeeSkill;
    }

}
