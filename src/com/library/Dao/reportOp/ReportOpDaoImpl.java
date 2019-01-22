package com.library.Dao.reportOp;

import com.library.pojo.Report;
import com.library.pojo.SeatRecord;
import com.library.utils.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;


public class ReportOpDaoImpl implements ReportOpDao{
    @Override
    public void subReport(String rUser,String redUser,String seatId,String reason){
        Session session=HibernateUtils.getSession();
        session.beginTransaction();

        Report report=new Report();
        report.setReportUser(rUser);
        report.setReportedUser(redUser);
        report.setSeatId(seatId);
        report.setReason(reason);
        report.setDate(new Date());
        report.setStatus(1);

        session.save(report);

        session.getTransaction().commit();

    }

    @Override
    public String findUser(String seatId) {//查找座位当前就做的学生
        Session session=HibernateUtils.getSession();

        String hql="from SeatRecord s where s.seatId=? and s.status=?";

        Query query=session.createQuery(hql);
        query.setParameter(0,seatId);
        query.setParameter(1,false);

        List<SeatRecord> list=query.list();

        if (list.size() != 0) {
            SeatRecord seatRecord=list.get(0);
            System.out.println(1);
            return seatRecord.getuId();
        }
        return null;
    }



    @Override
    public List<Report> showReports() {//显示举报内容
        Session session=HibernateUtils.getSession();
        String hql="from Report r order by r.status desc ";

       Query query=session.createQuery(hql);

       List<Report> reports=query.list();

       if(reports.size()!=0){
           return reports;
       }

        return null;
    }

    @Override
    public int minus(String userId, int reportId) {//扣取被举报人的分数
        Session session=HibernateUtils.getSession();
        Transaction trans=session.beginTransaction();
        String hql="update User u set u.credit=u.credit-10 where u.sid=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,userId);
        int r=query.executeUpdate();
        trans.commit();
        changeStatus(reportId,-1);
        return r;
    }

    @Override
    public int ignore(int reportId) {//忽略该举报
        int c=changeStatus(reportId,0);
        return c;
    }

    @Override
    public int changeStatus(int reportId,int status) {//改变举报的状态,1为为处理，0为忽略，-1为已扣分
        Session session=HibernateUtils.getSession();
        Transaction trans=session.beginTransaction();
        String hql="update Report r set r.status=? where r.reportId=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,status);
        query.setParameter(1,reportId);
        int c=query.executeUpdate();
        trans.commit();
        return c;
    }


}
