package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetService;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        userService.saveCustomer(convertCustomerDTOToCustomer(customerDTO));
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        Iterable<Customer> customers = userService.getAllCustomers();
        List<CustomerDTO> listOfCustomers = new ArrayList<CustomerDTO>();

        for (Customer c : customers) {
            c.setPets(petService.getPetsByOwner(c.getId()));
            listOfCustomers.add(convertCustomerToCustomerDTO(c));
        }

        return listOfCustomers;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        CustomerDTO customerDTO = null;

        if (pet != null) {
            Customer customer = userService.getCustomer(pet.getCustomer().getId()).get();
            customerDTO = convertCustomerToCustomerDTO(customer);
        } else {
            throw new UnsupportedOperationException();
        }
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        userService.saveEmployee(convertEmployeeDTOToEmployee(employeeDTO));
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = userService.getEmployee(employeeId).get();
        EmployeeDTO employeeDTO = null;

        if(employee != null) {
            employeeDTO = convertEmployeeToEmployeeDTO(employee);
        } else {
            throw new UnsupportedOperationException();
        }
        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customer.setPets(petService.getPetsByOwner(customer.getId()));
        List<Long> petsIds = new ArrayList<Long>();
        for (Pet p : customer.getPets()) {
            petsIds.add(p.getId());
        }
        customerDTO.setPetIds(petsIds);
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

}
