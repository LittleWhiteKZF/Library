package com.library.service.reportOp;

import com.library.pojo.Report;

import java.util.List;

public interface ReportOpService {
    void subReport(String rUser,String redUser,String seatId,String reason);
    String findUser(String seatId);
    List<Report> showReports();
    int minus(String userId, int reportId);
    int ignore(int reportId);
}
