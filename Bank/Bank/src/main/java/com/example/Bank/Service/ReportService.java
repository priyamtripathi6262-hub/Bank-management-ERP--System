package com.example.Bank.Service;

import java.util.List;
import java.util.Map;

public interface ReportService {

    List<Map<String, Object>> fetchReport(String reportType, String fromDate, String toDate, String accNo);

    boolean verifyAccountNo(String accNo);
}