package com.example.Bank.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Bank.Service.BankService;
import com.example.Bank.Entity.AccountMaster;
import com.example.Bank.Entity.AccountTypeMaster;
import com.example.Bank.Entity.Customerid_detail;

@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = "*")
public class BankController {

    @Autowired
    private BankService service; 
    
    @PostMapping("/save")
    public AccountMaster saveData(@RequestBody AccountMaster account){
        return service.saveAccount(account);
    }

    @GetMapping("/all")
    public List<AccountMaster> getData(){
        return service.getAllAccount();
    }

    @PostMapping("/type")
    public AccountTypeMaster addType(@RequestBody AccountTypeMaster type) {
        return service.saveAccountType(type);
    }

    @GetMapping("/get-all-types") 
    public List<AccountTypeMaster> showTypes() {
        return service.getAllAccountTypes();
    }

    @PostMapping("/create-customer")
    public String createCustomer(@RequestBody Customerid_detail user) {
        return service.create(user);
    }

    @GetMapping("/get-all-customers")
    public List<Customerid_detail> getAllCustomers() {
        return service.getAllCustomers();
    }

    @PostMapping("/path")
    public String Open(@RequestBody AccountMaster account){
        return service.Open(account);
    }

    
}