package com.udacity.jdnd.course3.critter.schedule;

import java.util.ArrayList;
import java.util.List;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        List<Schedule> schedules = getAllSchedules();
        List<Schedule> petSchedules = new ArrayList<Schedule>();

        for(Schedule s : schedules) {
            for(Pet p : s.getPets()) {
                if(p.getId() == petId) {
                    petSchedules.add(s);
                }
            }
        }
        return petSchedules;
    }

    public Schedule getScheduleForPet2(long petId) {
        List<Schedule> schedules = getAllSchedules();
        Schedule petSchedules = null;

        for (Schedule s : schedules) {
            for (Pet p : s.getPets()) {
                if (p.getId() == petId) {
                    petSchedules = s;
                }
            }
        }
        return petSchedules;
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        List<Schedule> schedules = getAllSchedules();
        List<Schedule> employeeSchedules = new ArrayList<Schedule>();

        for (Schedule s : schedules) {
            for (Employee e : s.getEmployees()) {
                if (e.getId() == employeeId) {
                    employeeSchedules.add(s);
                }
            }
        }
        return employeeSchedules;
    }

}
