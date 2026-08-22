package com.example.Bank.Service;

import java.util.List;
import com.example.Bank.Entity.Customerid_detail;

public interface CreateCustomerService {

    Customerid_detail createCustomer(Customerid_detail customer);

    Customerid_detail getCustomerById(Long id);

  
    List<Customerid_detail> getAllCustomers();
}