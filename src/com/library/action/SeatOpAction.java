package com.library.action;
import com.library.pojo.Seat;
import com.library.pojo.SeatRecord;
import com.library.pojo.User;
import com.library.service.seatOperation.SeatOpServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SeatOpAction extends ActionSupport {
    private int floor;//楼层
    private boolean status;//座位状态
    private String seatIn;//入座座位
    private int seatingTime;//入座时间
    private double studyTime;
    private String result;
    User user= (User) ActionContext.getContext().getSession().get("user");

    public void showSeats(){
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        HashMap<String,List<Seat>> seats=seatOpService.showSeats();
        ActionContext.getContext().getSession().put("seats",seats);
    }

    public String leaveSeat(){
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        sdf.format(date);
        seatOpService.Leave(seatIn,user.getSid(),date);
        System.out.println(seatIn+" "+user.getSid()+" "+date);
        seatOpService.canIn(seatIn);//离座后座位变为可入座
        seatOpService.caculateTime(studyTime,user.getSid());//计算总学习时间
        System.out.println(studyTime);
        return "leave";
    }

    public String remove(){
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        sdf.format(date);
        User u=seatOpService.searchUser(seatIn);
        seatOpService.Leave(seatIn,u.getSid(),date);
        seatOpService.canIn(seatIn);
        return "leave";

    }

    public void shortLeave(){//用户暂离
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        int c=seatOpService.shortLeave(seatIn);
        if(c<=0){
            System.out.println("error");
        }
    }

    public void comeback(){//用户回座
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        int c=seatOpService.comeback(seatIn);
        if(c<=0)
            System.out.println("error");
    }

    public String isShortLeave(){//判断用户是否暂离
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        boolean shortLeave=seatOpService.isShortLeave(seatIn);
        if(shortLeave){
            result="leave";
        }else result="notleave";
        return "success";
    }




    public String search(){//查找当前该座位上的用户的id和姓名
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        User u=seatOpService.searchUser(seatIn);
        String userId=u.getSid();
        String userName=u.getName();
        result=userId+","+userName;
        return "success";

    }

    public void getRecord(){
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        List<SeatRecord> list=seatOpService.getRecord();
        ActionContext.getContext().getSession().put("record",list);
    }

    public boolean enterOrNot(){//判断当前该用户是否已经入座
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        return seatOpService.enterOrNot(user.getSid());
    }

    public String backToCountdown(){//判断当前该座位是否被该用户占用
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        User u=seatOpService.searchUser(seatIn);
        if(u.getSid().equals(user.getSid())){
            result="back";
        }
        else result="cannotback";
        return "success";
    }

    public String isLeave(){
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        if(seatOpService.isLeave(seatIn)){
           result="true";
        }
        else result="false";
        return "success";
    }


    public void setSeatIn(){//设置当前入座的座位号，防止退出或刷新倒计时页面后座位号获取不到
        seatIn= (String) ActionContext.getContext().getSession().get("seatIn");
    }



    @Override
    public String execute() throws Exception {
        SeatOpServiceImpl seatOpService=new SeatOpServiceImpl();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        sdf.format(date);
        seatOpService.InSeat(seatIn,user.getSid(),date);
        ActionContext.getContext().getSession().put("seatIn",seatIn);
        ActionContext.getContext().getSession().put("seatTime",seatingTime);
        System.out.println(seatingTime);
        return SUCCESS;
    }


    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSeatIn() {
        return seatIn;
    }

    public void setSeatIn(String seatIn) {
        this.seatIn = seatIn;
    }

    public int getSeatingTime() {
        return seatingTime;
    }

    public void setSeatingTime(int seatingTime) {
        this.seatingTime = seatingTime;
    }

    public double getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(double studyTime) {
        this.studyTime = studyTime;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
