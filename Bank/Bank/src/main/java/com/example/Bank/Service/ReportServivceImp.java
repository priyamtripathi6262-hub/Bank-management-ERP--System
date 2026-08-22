package com.example.Bank.Service;
import com.example.Bank.Repositry.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class ReportServivceImp implements ReportService {

    @Autowired
    private AccountRepository accountRepository;

    private static final DateTimeFormatter DATE_ONLY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    @Override
    public List<Map<String, Object>> fetchReport(String reportType, String fromDateStr, String toDateStr, String accNo) {
        
        LocalDateTime fromDate;
        LocalDateTime toDate;

        
        try {
            if (fromDateStr.contains(" ")) {
                fromDate = LocalDateTime.parse(fromDateStr, DATE_TIME_FORMATTER);
            } else {
                fromDate = LocalDate.parse(fromDateStr, DATE_ONLY_FORMATTER).atStartOfDay(); // 00:00:00
            }

            if (toDateStr.contains(" ")) {
                toDate = LocalDateTime.parse(toDateStr, DATE_TIME_FORMATTER);
            } else {
                toDate = LocalDate.parse(toDateStr, DATE_ONLY_FORMATTER).atTime(LocalTime.MAX); // 23:59:59
            }
        } catch (Exception e) {
            throw new RuntimeException("Date format sahi nahi hai! Expected 'yyyy-MM-dd' ya 'yyyy-MM-dd HH:mm:ss'");
        }

        
        if ("INDIVIDUAL".equalsIgnoreCase(reportType) && accNo != null && !accNo.trim().isEmpty()) {
            return accountRepository.fetchIndividualAccountReportMap(fromDate, toDate, accNo.trim());
        }

        return accountRepository.fetchAccountMasterReportMap(fromDate, toDate);
    }

    @Override
    public boolean verifyAccountNo(String accNo) {
        if (accNo == null || accNo.trim().isEmpty()) {
            return false;
        }
        return accountRepository.findByAcno(accNo.trim()).isPresent();
    }
}