package com.udacity.jdnd.course3.critter.user;

import javax.transaction.Transactional;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
        //return customer.getId();
    }

}
