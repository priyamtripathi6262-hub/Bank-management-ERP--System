package com.example.Bank.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Bank.Entity.Customerid_detail;
import com.example.Bank.Repositry.CustomerRepository;

@Service
public class CreateCustomerServiceImp implements CreateCustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CreateCustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customerid_detail createCustomer(Customerid_detail customer) {
        if (customer.getStatus() == null || customer.getStatus().trim().isEmpty()) {
            customer.setStatus("ACTIVE"); 
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customerid_detail getCustomerById(Long id) {
        if (id == null) {
            throw new RuntimeException("Provided Customer ID is invalid!");
        }
        return customerRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Customer reference ID #" + id + " database mein nahi mila!"));
    }

    @Override
    public List<Customerid_detail> getAllCustomers() {
        return customerRepository.findAll();
    }
}