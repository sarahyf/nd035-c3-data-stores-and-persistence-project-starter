package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.CustomerRepository;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class UserService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomer(long customerId) {
        return customerRepository.findById(customerId);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployee(long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void updateEmployeeSchedule(Set<DayOfWeek> daysAvailable, long employeeId) {
        employeeRepository.getOne(employeeId).setDaysAvailable(daysAvailable);
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        List<Employee> employees = getAllEmployees();
        List<Employee> availableEmployees = new ArrayList<Employee>();

        for (Employee e : employees) {
            if(e.getSkills().size() == skills.size()) {
                if (e.getSkills().equals(skills) && e.getDaysAvailable().contains(dayOfWeek)) {
                    availableEmployees.add(e);
                }
            } else {
                if (CollectionUtils.containsAny(e.getSkills(), (skills)) && e.getDaysAvailable().contains(dayOfWeek)) {
                    availableEmployees.add(e);
                }
            }
        }
        return availableEmployees;
    }

}
