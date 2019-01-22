package com.library.Dao.reportOp;

import com.library.pojo.Report;

import java.util.List;

public interface ReportOpDao {
    void subReport(String rUser,String redUser,String seatId,String reason);
    String findUser(String seatId);
    List<Report> showReports();
    int minus(String userId,int reportId);
    int ignore(int reportId);
    int changeStatus(int reportId,int status);
}
