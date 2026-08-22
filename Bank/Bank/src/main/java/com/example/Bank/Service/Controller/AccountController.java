package com.example.Bank.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Entity.AccountMaster;
import com.example.Bank.Service.CreateAccountService;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private CreateAccountService accountService;

    @PostMapping
    public ResponseEntity<AccountMaster> createAccount(@RequestBody AccountMaster account) {
        AccountMaster savedAccount = accountService.createAccount(account);
        return ResponseEntity.ok(savedAccount);
    }

    @GetMapping("/{accNo}")
    public ResponseEntity<AccountMaster> getAccountByAcno(@PathVariable String accNo) {
        AccountMaster account = accountService.getAccountByAcno(accNo);
        return ResponseEntity.ok(account);
    }
}