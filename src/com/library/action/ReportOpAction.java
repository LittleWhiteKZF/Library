package com.library.action;

import com.library.pojo.Report;
import com.library.pojo.User;
import com.library.service.reportOp.ReportOpServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;


public class ReportOpAction extends ActionSupport {
    private String seatId;
    private String reason;
    private int reportId;
    private String userId;
    private String result;

    public String subReport() {

        System.out.println(seatId);
        System.out.println(reason);
        User user = (User) ActionContext.getContext().getSession().get("user");
        ReportOpServiceImpl reportOpService = new ReportOpServiceImpl();
        String redUser = reportOpService.findUser(seatId);
        if (redUser == null) {
            System.out.println("error");
            result="提交失败";

        } else {
            reportOpService.subReport(user.getSid(), redUser, seatId, reason);
            System.out.println("success");
            result="提交成功";
        }
        return "success";
    }

    public void showReport(){
        ReportOpServiceImpl reportOpService=new ReportOpServiceImpl();
        List<Report> reports=reportOpService.showReports();
        ActionContext.getContext().getSession().put("reports",reports);
    }

    public String minus(){
        ReportOpServiceImpl reportOpService=new ReportOpServiceImpl();
        int c=reportOpService.minus(userId,reportId);
        if(c>0){
            System.out.println("扣分成功");
        }
        return "success";
    }

    public String ignore(){
        ReportOpServiceImpl reportOpService=new ReportOpServiceImpl();
        int c=reportOpService.ignore(reportId);
        if(c>0)
            System.out.println("忽略成功");
        return "success";
    }




    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
