package com.example.Bank.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Bank.Entity.AccountMaster;
import com.example.Bank.Repositry.AccountRepository;

@Service
public class CreateAccountServiceImp implements CreateAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional 
    public AccountMaster createAccount(AccountMaster account) {
        
        String inputAccType = account.getAcctype(); 
        if (inputAccType == null || inputAccType.trim().isEmpty()) {
            inputAccType = "SB";
        }
        
        String numericPrefix = "10"; 
        
        if (inputAccType.equalsIgnoreCase("SB")) {
            numericPrefix = "10"; 
        } else if (inputAccType.equalsIgnoreCase("CA")) {
            numericPrefix = "01"; 
        } else if (inputAccType.equalsIgnoreCase("RD")) {
            numericPrefix = "08"; 
        } else if (inputAccType.equalsIgnoreCase("FD")) {
            numericPrefix = "07"; 
        }

        String top = "90" + numericPrefix; 
        
        Integer dbMax = null;
        try {
            dbMax = accountRepository.getmax();
        } catch (Exception e) {
            dbMax = null; 
        }
        
        int maxNumber = (dbMax != null) ? dbMax + 1 : 1;
        String paddedNumber = String.format("%06d", maxNumber);
        String generatedAcno = top + paddedNumber + "01"; 
        
        account.setAcno(generatedAcno);
        account.setAcctype(inputAccType); 
        account.setOpeningDt(LocalDateTime.now());
        account.setLastTranDt(LocalDateTime.now());
        
        if (account.getAmount() == null) {
            account.setAmount(0.0);
        }
        
        return accountRepository.save(account);
    }

    @Override
    public AccountMaster getAccountByAcno(String accNo) {
        return accountRepository.findByAcno(accNo)
                .orElseThrow(() -> new RuntimeException("Account No " + accNo + " nahi mila!"));
    }
}