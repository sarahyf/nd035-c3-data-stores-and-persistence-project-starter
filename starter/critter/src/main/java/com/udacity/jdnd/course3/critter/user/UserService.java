package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
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

@Service
public class UserService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> getCustomer(long customerId) {
        return customerRepository.findById(customerId);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployee(long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public void updateEmployeeSchedule(Set<DayOfWeek> daysAvailable, long employeeId) {
        // System.out.println(daysAvailable);
        // System.out.println(daysAvailable.iterator());

       employeeRepository.updateEmployeeSchedule(daysAvailable, employeeId);
    // employeeRepository.updateEmployeeSchedule(employeeId, "S");
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        return employeeRepository.findEmployeesForService(skills, dayOfWeek);
    }

}
