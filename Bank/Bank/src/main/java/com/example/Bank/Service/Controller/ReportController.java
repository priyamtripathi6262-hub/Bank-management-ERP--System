package com.example.Bank.Controller;

import com.example.Bank.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    
    @GetMapping("/all")
    public List<Map<String, Object>> getAllReport(
            @RequestParam String fromDate, 
            @RequestParam String toDate) {
        
        return reportService.fetchReport("ALL", fromDate, toDate, null); 
    }

    
    @GetMapping("/individual")
    public List<Map<String, Object>> getIndividualReport(
            @RequestParam String fromDate, 
            @RequestParam String toDate,
            @RequestParam String accNo) {
        
        return reportService.fetchReport("INDIVIDUAL", fromDate, toDate, accNo);
    }

    @GetMapping("/verify-account")
    public boolean verifyAccount(@RequestParam String accNo) {
        
        return reportService.verifyAccountNo(accNo);
    }
}