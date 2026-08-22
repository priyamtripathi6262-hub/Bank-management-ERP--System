package com.example.Bank.Service;

import com.example.Bank.Entity.AccountMaster;

public interface CreateAccountService {
    AccountMaster createAccount(AccountMaster account);
    AccountMaster getAccountByAcno(String accNo); 
}