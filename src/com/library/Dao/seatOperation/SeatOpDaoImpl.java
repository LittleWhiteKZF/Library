package com.library.Dao.seatOperation;

import com.library.Dao.reportOp.ReportOpDaoImpl;
import com.library.pojo.Seat;
import com.library.pojo.SeatRecord;
import com.library.pojo.User;
import com.library.utils.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SeatOpDaoImpl implements SeatOpDao {
    @Override
    public HashMap showSeats() {
        String hql = "from Seat s";
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery(hql);
        List<Seat> list = query.list();
        List<Seat> list1 = new ArrayList<>();
        List<Seat> list2 = new ArrayList<>();
        HashMap<String, List<Seat>> seatMap = new HashMap<>();
        if (list.size() != 0) {
            for (Seat seat : list) {
                if (seat.getFloor() == 1) {
                    list1.add(seat);
                } else {
                    list2.add(seat);
                }
            }
            seatMap.put("1", list1);
            seatMap.put("2", list2);
            return seatMap;
        }

        return null;
    }

    @Override
    public void InSeat(String seatId, String sId, Date sTime) {//入座
        Session session = HibernateUtils.getSession();
        session.beginTransaction();

        String hql = "update Seat s set s.status=? where s.seatId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, false);
        query.setParameter(1, seatId);
        query.executeUpdate();

        SeatRecord seatRecord = new SeatRecord();
        seatRecord.setSeatId(seatId);
        seatRecord.setuId(sId);
        seatRecord.setStartTime(sTime);
        seatRecord.setStatus(false);
        seatRecord.setIsout(0);
        session.save(seatRecord);

        session.getTransaction().commit();


    }

    @Override
    public void Leave(String seatId, String sid, Date leave) {//离座插入离座时间
        Session session = HibernateUtils.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "update SeatRecord seat set seat.leaveTime=?,seat.status=? where seat.seatId=? and seat.uId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, leave);
        query.setParameter(1, true);
        query.setParameter(2, seatId);
        query.setParameter(3, sid);
        query.executeUpdate();
        trans.commit();
    }

    @Override
    public int canIn(String seatId) {//离座后座位状态变为可入座
        Session session = HibernateUtils.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "update Seat s set s.status=? where s.seatId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, true);
        query.setParameter(1, seatId);
        int c = query.executeUpdate();
        trans.commit();
        return c;
    }

    @Override
    public int caculateTime(double studyTime, String sId) {//计算学习总时长
        Session session = HibernateUtils.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "update User u set u.time=u.time+? where u.sid=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, studyTime);
        query.setParameter(1, sId);
        int c = query.executeUpdate();
        System.out.println(c);
        trans.commit();
        return c;
    }

    @Override
    public List<SeatRecord> getRecord() {
        Session session = HibernateUtils.getSession();
        String hql = "from SeatRecord r where r.status=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, false);
        List<SeatRecord> list = query.list();
        if (list.size() != 0) {
            return list;
        }
        return null;
    }

    @Override
    public User searchUser(String seatId) {//查找该座位上学生
        Session session = HibernateUtils.getSession();
        String hql = "from User u where u.sid=?";
        ReportOpDaoImpl reportOpDao = new ReportOpDaoImpl();
        String sid = reportOpDao.findUser(seatId);
        Query query = session.createQuery(hql);
        query.setParameter(0, sid);
        List<User> list = query.list();
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean enterOrNot(String sid) {//判断该学生是否已经入座
        Session session = HibernateUtils.getSession();
        String hql = "from SeatRecord s where s.uId=? and s.status=false";
        Query query = session.createQuery(hql);
        query.setParameter(0, sid);
        List<SeatRecord> list = query.list();
        if (list.size() == 0) return true;
        return false;
    }

    @Override
    public boolean isLeave(String seatId) {
        Session session = HibernateUtils.getSession();
        String hql = "from Seat s where s.seatId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, seatId);
        Seat seat = (Seat) query.list().get(0);
        return seat.isStatus();
    }

    @Override
    public int shortLeave(String seatId) {//用户暂离
        Session session = HibernateUtils.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "update SeatRecord s set s.isout=1 where s.seatId=? and s.status=false";
        Query query = session.createQuery(hql);
        query.setParameter(0, seatId);
        int c = query.executeUpdate();
        trans.commit();
        return c;
    }

    @Override
    public int comeback(String seatId) {//用户回座
        Session session = HibernateUtils.getSession();
        Transaction trans = session.beginTransaction();
        String hql = "update SeatRecord seat set seat.isout=0 where seat.seatId=? and seat.status=false";
        Query query = session.createQuery(hql);
        query.setParameter(0, seatId);
        int q = query.executeUpdate();
        trans.commit();
        return q;

    }


    @Override
    public boolean isShortLeave(String seatId) {//查看用户是否暂离
        Session session = HibernateUtils.getSession();
        String hql = "from SeatRecord s where s.seatId=? and s.status=false";
        Query query = session.createQuery(hql);
        query.setParameter(0, seatId);
        List<SeatRecord> list = query.list();
        SeatRecord seatRecord = list.get(0);
        int l = seatRecord.getIsout();
        if (l == 1) return true;
        return false;
    }


}
