package com.example.Bank.Service;
import java.util.List;

import com.example.Bank.Entity.AccountMaster;
import com.example.Bank.Entity.AccountTypeMaster;
import com.example.Bank.Entity.Customerid_detail;


public interface BankService {
    
    AccountMaster saveAccount(AccountMaster account);
    List<AccountMaster> getAllAccount();

    AccountTypeMaster saveAccountType(AccountTypeMaster accType);
    List<AccountTypeMaster> getAllAccountTypes();

    List<Customerid_detail> getAllCustomers();
    String create(Customerid_detail user);
    
    public String Open(AccountMaster account);
    public String generateAccountNo(String acctype); 
}