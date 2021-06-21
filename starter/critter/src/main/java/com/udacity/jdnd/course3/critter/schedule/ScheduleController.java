package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetService;
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
        return convertScheduleToScheduleDTO(scheduleService.createSchedule(convertScheduleDTOToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> listOfSchedules = new ArrayList<ScheduleDTO>();

        for (Schedule s : schedules) {
            listOfSchedules.add(convertScheduleToScheduleDTO(s));
        }

        return listOfSchedules;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return convertSpecificSchedulesToDTO(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return convertSpecificSchedulesToDTO(scheduleService.getScheduleForEmployee(employeeId));
    }

    public List<ScheduleDTO> convertSpecificSchedulesToDTO(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
        if (!schedules.isEmpty()) {
            for (Schedule s : schedules) {
                scheduleDTOs.add(convertScheduleToScheduleDTO(s));
            }
        }
        return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Pet> pets = petService.getPetsByOwner(customerId);
        List<List<ScheduleDTO>> scheduleDTOs = new ArrayList<List<ScheduleDTO>>();
        
        List<ScheduleDTO> allScheduleDTOs = new ArrayList<ScheduleDTO>();

        for(Pet p : pets) {
            scheduleDTOs.add(convertSpecificSchedulesToDTO(scheduleService.getScheduleForPet(p.getId())));
        }

        for(List<ScheduleDTO> listOfSchedules : scheduleDTOs) {
            for(ScheduleDTO aSchedule : listOfSchedules) {
                allScheduleDTOs.add(aSchedule);
            }
        }

        return allScheduleDTOs;
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
