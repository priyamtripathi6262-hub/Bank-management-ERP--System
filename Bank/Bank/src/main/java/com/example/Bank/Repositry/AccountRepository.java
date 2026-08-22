package com.example.Bank.Repositry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Bank.Entity.AccountMaster;

@Repository
public interface AccountRepository extends JpaRepository<AccountMaster, Long> {

    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING(acno, 5, 6) AS UNSIGNED)), 0) FROM account_master", nativeQuery = true)
    Integer getmax();

    Optional<AccountMaster> findByAcno(String acno);

    // 1. ALL Accounts Report Query
    @Query(value = "SELECT " +
                   "    b.cust_id, " +
                   "    b.acno AS account_no, " +
                   "    a.full_name, " +
                   "    a.email, " +
                   "    a.mobile_no, " +
                   "    a.pan_no, " +
                   "    c.cramt, " +
                   "    c.dramt, " +
                   "    c.tran_type, " +
                   "    c.dt, " +
                   "    IFNULL(d.balance, 0.0) AS balance " +
                   "FROM customerid_detail AS a " +
                   "JOIN account_master b ON a.cust_id = b.cust_id " +
                   "LEFT JOIN ( " +
                   "    SELECT " +
                   "        acc_no, " +
                   "        cramt, " +
                   "        dramt, " +
                   "        tran_type, " +
                   "        DATE_FORMAT(transaction_Dt, '%Y%m%d') AS dt " +
                   "    FROM recon " +
                   "    WHERE transaction_Dt >= :fromDate " +
                   "      AND transaction_Dt <= :toDate " +
                   ") AS c ON b.acno = c.acc_no " +
                   "LEFT JOIN ( " +
                   "    SELECT " +
                   "        acc_no, " +
                   "        IFNULL(SUM(IFNULL(cramt, 0) - IFNULL(dramt, 0)), 0) AS balance " +
                   "    FROM recon " +
                   "    WHERE transaction_Dt >= :fromDate " +
                   "      AND transaction_Dt <= :toDate " +
                   "    GROUP BY acc_no " +
                   ") AS d ON b.acno = d.acc_no " +
                   "WHERE (c.cramt IS NOT NULL OR c.dramt IS NOT NULL) " +
                   "ORDER BY b.acno", 
           nativeQuery = true)
    List<Map<String, Object>> fetchAccountMasterReportMap(@Param("fromDate") LocalDateTime fromDate, 
                                                         @Param("toDate") LocalDateTime toDate);

    // 2. INDIVIDUAL Account Report Query
    @Query(value = "SELECT " +
                   "    b.cust_id, " +
                   "    b.acno AS account_no, " +
                   "    a.full_name, " +
                   "    a.email, " +
                   "    a.mobile_no, " +
                   "    a.pan_no, " +
                   "    c.cramt, " +
                   "    c.dramt, " +
                   "    c.tran_type, " +
                   "    c.dt, " +
                   "    IFNULL(d.balance, 0.0) AS balance " +
                   "FROM customerid_detail AS a " +
                   "JOIN account_master b ON a.cust_id = b.cust_id " +
                   "LEFT JOIN ( " +
                   "    SELECT " +
                   "        acc_no, " +
                   "        cramt, " +
                   "        dramt, " +
                   "        tran_type, " +
                   "        DATE_FORMAT(transaction_Dt, '%Y%m%d') AS dt " +
                   "    FROM recon " +
                   "    WHERE acc_no = :accNo " +
                   "      AND transaction_Dt >= :fromDate " +
                   "      AND transaction_Dt <= :toDate " +
                   ") AS c ON b.acno = c.acc_no " +
                   "LEFT JOIN ( " +
                   "    SELECT " +
                   "        acc_no, " +
                   "        IFNULL(SUM(IFNULL(cramt, 0) - IFNULL(dramt, 0)), 0) AS balance " +
                   "    FROM recon " +
                   "    WHERE acc_no = :accNo " +
                   "      AND transaction_Dt >= :fromDate " +
                   "      AND transaction_Dt <= :toDate " +
                   "    GROUP BY acc_no " +
                   ") AS d ON b.acno = d.acc_no " +
                   "WHERE b.acno = :accNo", 
           nativeQuery = true)
    List<Map<String, Object>> fetchIndividualAccountReportMap(@Param("fromDate") LocalDateTime fromDate, 
                                                             @Param("toDate") LocalDateTime toDate,
                                                             @Param("accNo") String accNo);
}