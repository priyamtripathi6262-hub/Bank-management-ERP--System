package com.example.Bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Entity.Recon;
import com.example.Bank.Service.ReconService;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/transactions")
public class ReconController {

    @Autowired
    private ReconService reconService;

   
    @PostMapping("/recon")
    public ResponseEntity<?> executeTransaction(@RequestBody Recon recon) {
        try {
            Recon result = reconService.createTransaction(recon);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}