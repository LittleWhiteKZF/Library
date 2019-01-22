package com.library.Dao.changePwd;


import com.library.pojo.Administrator;
import com.library.pojo.User;
import com.library.utils.HibernateUtils;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static com.library.utils.HibernateUtils.getSession;


public class ChangePwdDaoImpl implements ChangePwdDao {
    @Override
    public int changePwd( String newPwd) {
        Map userSession = ActionContext.getContext().getSession();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) userSession.get("user");
        String hql = "update User u set u.password=? where u.sid=?";
        Query query = session.createQuery(hql);
        String sid = user.getSid();
        query.setParameter(0, newPwd);
        query.setParameter(1, sid);
        int c = query.executeUpdate();
        transaction.commit();
        return c;
    }
    @Override
    public int changeAdminPwd(String newPwd){
        Map userSession = ActionContext.getContext().getSession();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Administrator administrator = (Administrator) userSession.get("admin");
        String hql = "update Administrator a set a.password=? where a.adminId=?";
        Query query = session.createQuery(hql);
        String sid = administrator.getAdminId();
        query.setParameter(0, newPwd);
        query.setParameter(1, sid);
        int c = query.executeUpdate();
        transaction.commit();
        return c;
    }

    @Override
    public String searchPwd(String id) {
        Session session=HibernateUtils.getSession();
        String hql="from User u where u.sid=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,id);
        List<User> list =query.list();
        User user=list.get(0);
        return user.getPassword();
    }

    @Override
    public String searchAdminPwd(String id) {
        Session session=HibernateUtils.getSession();
        String hql="from Administrator a where a.adminId=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,id);
        List<Administrator> list =query.list();
        Administrator admin=list.get(0);
        return admin.getPassword();

    }

}
