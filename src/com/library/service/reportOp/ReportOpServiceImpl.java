package com.library.service.reportOp;

import com.library.Dao.reportOp.ReportOpDaoImpl;
import com.library.pojo.Report;
import com.library.pojo.SeatRecord;

import java.util.List;

public class ReportOpServiceImpl implements ReportOpService {//插入举报信息
    @Override
    public void subReport(String rUser,String redUser,String seatId,String reason){
        ReportOpDaoImpl reportOpDao=new ReportOpDaoImpl();
        reportOpDao.subReport(rUser,redUser,seatId,reason);
    }

    @Override
    public String findUser(String seatId) {
        ReportOpDaoImpl reportOpDao=new ReportOpDaoImpl();
        String uId=reportOpDao.findUser(seatId);
        return uId;
    }

    @Override
    public List<Report> showReports() {
        ReportOpDaoImpl reportOpDao=new ReportOpDaoImpl();
        List<Report> reportList=reportOpDao.showReports();
        if(reportList!=null)
            return reportList;
        return null;
    }

    @Override
    public int minus(String userId, int reportId) {
        ReportOpDaoImpl reportOpDao=new ReportOpDaoImpl();
        int c=reportOpDao.minus(userId,reportId);
        return c;
    }

    @Override
    public int ignore(int reportId) {
        ReportOpDaoImpl reportOpDao=new ReportOpDaoImpl();
        int c=reportOpDao.ignore(reportId);
        return c;
    }
}
