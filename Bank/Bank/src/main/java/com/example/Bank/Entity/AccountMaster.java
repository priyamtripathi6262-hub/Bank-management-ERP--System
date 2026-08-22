package com.example.Bank.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_master")
public class AccountMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    private String acno; 
    private String custName; 
    
    private LocalDateTime openingDt; 
    private LocalDateTime closingDt;
    private LocalDateTime lastTranDt;
    
    private Long custId; 
    private String acctype; 
    private Double amount; 

  
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAcno() {
        return acno;
    }
    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    
    public LocalDateTime getOpeningDt() {
        return openingDt;
    }
    public void setOpeningDt(LocalDateTime openingDt) {
        this.openingDt = openingDt;
    }
    
    public LocalDateTime getClosingDt() {
        return closingDt;
    }
    public void setClosingDt(LocalDateTime closingDt) {
        this.closingDt = closingDt;
    }
    
    public LocalDateTime getLastTranDt() {
        return lastTranDt;
    }
    public void setLastTranDt(LocalDateTime lastTranDt) {
        this.lastTranDt = lastTranDt;
    }
    
    public Long getCustId() {
        return custId;
    }
    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getAcctype() {
        return acctype;
    }
    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }
    
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}