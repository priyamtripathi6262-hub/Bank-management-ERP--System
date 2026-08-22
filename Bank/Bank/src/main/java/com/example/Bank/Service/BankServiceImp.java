package com.example.Bank.Service;

import java.time.LocalDateTime; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Bank.Entity.AccountMaster;
import com.example.Bank.Entity.AccountTypeMaster;
import com.example.Bank.Entity.Customerid_detail;
import com.example.Bank.Repositry.AccountRepository;
import com.example.Bank.Repositry.AccountTypeRepository;
import com.example.Bank.Repositry.CustomerRepository;

@Service
public class BankServiceImp implements BankService {

    @Autowired
    private AccountRepository accRepo; 

    @Autowired
    private AccountTypeRepository typeRepo; 

    @Autowired
    private CustomerRepository repo; 

    @Override
    public AccountMaster saveAccount(AccountMaster account) {
        return accRepo.save(account);
    }

    @Override
    public List<AccountMaster> getAllAccount() {
        return accRepo.findAll();
    }

    @Override
    public AccountTypeMaster saveAccountType(AccountTypeMaster accType) {
        return typeRepo.save(accType);
    }

    @Override
    public List<AccountTypeMaster> getAllAccountTypes() {
        return typeRepo.findAll(); 
    }

    @Override 
    @Transactional
    public String create(Customerid_detail user) {
        Customerid_detail savedRow = repo.save(user);
        return "Customer Created! ID: " + savedRow.getCustId();
    }

    @Override 
    public List<Customerid_detail> getAllCustomers() {
        return repo.findAll();
    }

    @Override
    @Transactional 
    public String Open(AccountMaster account) {
        AccountMaster data = new AccountMaster();
        
        String inputAccType = account.getAcctype(); 
        if (inputAccType == null || inputAccType.trim().isEmpty()) {
            inputAccType = "10"; 
        }

        String acno = generateAccountNo(inputAccType);

        data.setAcno(acno);
        data.setAcctype(inputAccType); 
        data.setClosingDt(account.getClosingDt());
        data.setCustId(account.getCustId());
        data.setCustName(account.getCustName());
        data.setLastTranDt(account.getLastTranDt());
        data.setOpeningDt(LocalDateTime.now()); 
        data.setAmount(account.getAmount() != null ? account.getAmount() : 0.0);

        AccountMaster saved = accRepo.save(data);

        return "Created Successfully..Your Id is :" + saved.getId();
    }

    @Override
    public String generateAccountNo(String acctype) {
        String top = "90" + acctype; 
        
        Integer dbMax = accRepo.getmax();
        int maxNumber = (dbMax != null) ? dbMax + 1 : 1;
        
        String paddedNumber = String.format("%06d", maxNumber);

        return top + paddedNumber + "01";
    }
}