package com.example.Bank.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Bank.Entity.AccountMaster;
import com.example.Bank.Entity.Recon;
import com.example.Bank.Repositry.AccountRepository;
import com.example.Bank.Repositry.ReconRepository;

@Service
public class ReconServiceImp implements ReconService {

    @Autowired
    private AccountRepository accountRepository; 

    @Autowired
    private ReconRepository reconRepository; 

    @Override
    @Transactional 
    public Recon createTransaction(Recon recon) {
        
        AccountMaster account = accountRepository.findByAcno(recon.getAccNo())
                .orElseThrow(() -> new RuntimeException("Invalid account number or account!"));
        
        if (account.getAmount() == null) {
            account.setAmount(0.0);
        }
        
        String rawTranType = recon.getTranType();
        if (rawTranType == null || rawTranType.trim().isEmpty()) {
            recon.setTranType("CASH"); // Default value
        } else {
            switch (rawTranType.trim()) {
                case "1": recon.setTranType("CASH"); break;
                case "2": recon.setTranType("TRANSFER"); break;
                case "3": recon.setTranType("ONLINE"); break;
                default:  recon.setTranType(rawTranType); break;
            }
        }

        
        double crAmt = recon.getCramt() != null ? recon.getCramt() : 0.0;
        double drAmt = recon.getDramt() != null ? recon.getDramt() : 0.0;
        
        if (crAmt > 0) {
            account.setAmount(account.getAmount() + crAmt);
        }

        if (drAmt > 0) {
            if (account.getAmount() < drAmt) {
                throw new RuntimeException("Account mein paryapt balance nahi hai!");
            }
            account.setAmount(account.getAmount() - drAmt);
        }

        
        recon.setTransactionDt(LocalDateTime.now());
        recon.setStatus("DONE");
        
        
        accountRepository.save(account); 
        return reconRepository.save(recon); 
    }
}