package com.udacity.jdnd.course3.critter.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // @OneToMany(mappedBy = "employee")
    // private List<Pet> pets;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Pet> pets;

    //@ManyToMany(mappedBy = "employees")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Skill> skills;

    @OneToMany(mappedBy = "employee")
    private List<Schedule> schedules;

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

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Schedule> getSchedules() {
        return this.schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

}
