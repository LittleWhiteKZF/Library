package com.library.service.seatOperation;

import com.library.Dao.seatOperation.SeatOpDao;
import com.library.Dao.seatOperation.SeatOpDaoImpl;
import com.library.pojo.Seat;
import com.library.pojo.SeatRecord;
import com.library.pojo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SeatOpServiceImpl implements SeatOpService{
    @Override
    public HashMap showSeats() {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        HashMap seatMap=seatOpDao.showSeats();
        return seatMap;
    }

    @Override
    public void InSeat(String seatId, String uId, Date startTime) {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        seatOpDao.InSeat(seatId,uId,startTime);
    }

    @Override
    public void Leave(String seatId, String uId, Date leave) {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        seatOpDao.Leave(seatId,uId,leave);
    }
    @Override
    public int canIn(String seatId){
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        int c=seatOpDao.canIn(seatId);
        return c;
    }
    @Override
    public int caculateTime(double studyTime,String sId){
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        int c=seatOpDao.caculateTime(studyTime,sId);
        return c;
    }



    @Override
    public List<SeatRecord> getRecord() {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        List<SeatRecord> list=seatOpDao.getRecord();
        if(list!=null){
            return list;
        }
        return null;
    }

    @Override
    public boolean enterOrNot(String sid) {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();

        return  seatOpDao.enterOrNot(sid);
    }

    @Override
    public boolean isLeave(String seatId) {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        return seatOpDao.isLeave(seatId);
    }

    @Override
    public int shortLeave(String seatId) {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        int c=seatOpDao.shortLeave(seatId);
        return c;
    }

    @Override
    public int comeback(String seatId) {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        int c=seatOpDao.comeback(seatId);
        return c;
    }

    @Override
    public boolean isShortLeave(String seatId) {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        return seatOpDao.isShortLeave(seatId);
    }

    @Override
    public User searchUser(String seatId) {
        SeatOpDaoImpl seatOpDao=new SeatOpDaoImpl();
        User user=seatOpDao.searchUser(seatId);
        return user;
    }
}
