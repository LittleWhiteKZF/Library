package com.library.Dao.seatOperation;

import com.library.pojo.Seat;
import com.library.pojo.SeatRecord;
import com.library.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface SeatOpDao {
    HashMap showSeats();//显示座位
    void InSeat(String seatId,String sId,Date sTime);//入座
    void Leave(String seatId,String sid,Date leave);
    int canIn(String seatId);//座位状态变为可入座
    int caculateTime(double studyTime,String sId);
    List<SeatRecord> getRecord();
    User searchUser(String seatId);
    boolean enterOrNot(String sid);
    boolean isLeave(String seatId);
    int shortLeave(String seatId);
    int comeback(String seatId);
    boolean isShortLeave(String seatId);
}
