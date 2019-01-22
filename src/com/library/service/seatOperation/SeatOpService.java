package com.library.service.seatOperation;

import com.library.pojo.Seat;
import com.library.pojo.SeatRecord;
import com.library.pojo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface SeatOpService {
    HashMap showSeats();

    void InSeat(String seatId, String uId, Date startTime);

    void Leave(String seatId, String uId, Date leave);

    int canIn(String seatId);

    int caculateTime(double studyTime, String sId);

    User searchUser(String seatId);

    List<SeatRecord> getRecord();

    boolean enterOrNot(String sid);

    boolean isLeave(String seatId);

    int shortLeave(String seatId);

    int comeback(String seatId);

    boolean isShortLeave(String seatId);
}
