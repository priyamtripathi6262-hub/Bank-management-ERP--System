package com.example.Bank.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recon")
public class Recon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String accNo;
    private Double cramt;
    private Double dramt; 
    
    private LocalDateTime transactionDt; 
    
    private String tranType; 
    
    private String status; 

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public Double getCramt() {
        return cramt;
    }

    public void setCramt(Double cramt) {
        this.cramt = cramt;
    }

    public Double getDramt() {
        return dramt;
    }

    public void setDramt(Double dramt) {
        this.dramt = dramt;
    }

    public LocalDateTime getTransactionDt() {
        return transactionDt;
    }

    public void setTransactionDt(LocalDateTime transactionDt) {
        this.transactionDt = transactionDt;
    }

    public String getTranType() {
        return tranType;
    }

    // Clean Setter (No Business Logic Here)
    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}