package com.udacity.jdnd.course3.critter.schedule;

import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.s;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.UserService;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserService userService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToSchedule(scheduleDTO);

        // for(Pet p : schedule.getPets()) {
        //     if(petService.getPet(p.getId()) == null) {
        //         throw new UnsupportedOperationException();
        //     }
        // }

        List<Employee> employees = userService.findEmployeesForService(schedule.getActivities(),
                schedule.getDate().getDayOfWeek());

        // for(Employee e : schedule.getEmployees()) {
        //     try {
        //             if(userService.getEmployee(e.getId()) == null || !employees.contains(e)) {
        //                 throw new UnsupportedOperationException();
        //             }
        //     } catch(NullPointerException exception) {
        //         throw new UnsupportedOperationException();
        //     }
        // }

        return convertScheduleToScheduleDTO(scheduleService.createSchedule(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        Iterable<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> listOfSchedules = new ArrayList<ScheduleDTO>();

        for (Schedule s : schedules) {
            listOfSchedules.add(convertScheduleToScheduleDTO(s));
        }

        return listOfSchedules;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> scheduleDTOs = null;
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);

        if(schedules != null) {
            scheduleDTOs = new ArrayList<ScheduleDTO>();
            for (Schedule s : schedules) {
                scheduleDTOs.add(convertScheduleToScheduleDTO(s));
            }
        }

        return scheduleDTOs;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> scheduleDTOs = null;
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);

        if(schedules != null) {
            scheduleDTOs = new ArrayList<ScheduleDTO>();
            for (Schedule s : schedules) {
                scheduleDTOs.add(convertScheduleToScheduleDTO(s));
            }
        }
        
        return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Pet> pets = petService.getPetsByOwner(customerId);
        List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();

        for(Pet p : pets) {
            for(Schedule s : scheduleService.getScheduleForPet(p.getId())) {
                scheduleDTOs.add(convertScheduleToScheduleDTO(s));
            }
        }

        return scheduleDTOs;
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        List<Long> pets = new ArrayList<Long>();
        List<Long> employees = new ArrayList<Long>();

        for (Pet p : schedule.getPets()) {
            pets.add(p.getId());
        }

        for (Employee e : schedule.getEmployees()) {
            employees.add(e.getId());
        }

        scheduleDTO.setPetIds(pets);
        scheduleDTO.setEmployeeIds(employees);

        BeanUtils.copyProperties(schedule, scheduleDTO);
        return scheduleDTO;
    }

    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        List<Pet> pets = new ArrayList<Pet>();
        List<Employee> employees = new ArrayList<Employee>();

        for(Long p : scheduleDTO.getPetIds()) {
            pets.add(petService.getPet(p));
        }

        for(Long e : scheduleDTO.getEmployeeIds()) {
            employees.add(userService.getEmployee(e).get());
        }

        schedule.setPets(pets);
        schedule.setEmployees(employees);

        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

}
