package com.example.Bank.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.Bank.Entity.AccountTypeMaster;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeMaster, Integer> {  
    
}