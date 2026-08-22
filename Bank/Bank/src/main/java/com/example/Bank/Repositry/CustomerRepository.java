package com.example.Bank.Repositry;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Bank.Entity.Customerid_detail;

@Repository
public interface CustomerRepository extends JpaRepository<Customerid_detail, Integer> {

    Optional<Customerid_detail> findByCustId(Integer custId);
}