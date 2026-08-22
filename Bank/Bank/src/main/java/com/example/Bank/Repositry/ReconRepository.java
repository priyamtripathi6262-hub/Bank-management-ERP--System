package com.example.Bank.Repositry;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bank.Entity.Recon;

@Repository
public interface ReconRepository extends JpaRepository<Recon, Long> {
    
  
    List<Recon> findByAccNo(String accNo);
    
    List<Recon> findByTranType(String tranType);
}