package com.library.Dao.login;


import com.library.pojo.Administrator;
import com.library.pojo.User;

import com.library.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;


public class LoginDaoImpl implements LoginDao {
    @Override
    public User login(String sid, String password) {

        Session session=HibernateUtils.getSession();
        String hql="from User u where u.sid=? and u.password=?";
        Query query= session.createQuery(hql);
        query.setParameter(0,sid);
        query.setParameter(1,password);
        List<User> list=query.list();
        if(list.size()!=0){
            User user=list.get(0);
            return user;
        }
        return null;
    }

    @Override
    public Administrator adminLogin(String adminId, String password) {
        Session session=null;
        session=HibernateUtils.getSession();
        String hql="from Administrator a where a.adminId=? and a.password=?";
        Query query= session.createQuery(hql);
        query.setParameter(0,adminId);
        query.setParameter(1,password);
        List<Administrator> list=query.list();
        if(list.size()!=0){
            Administrator administrator=list.get(0);
            return administrator;
        }

        return null;
    }

    @Override
    public User flushtudent(String sid) {
        Session session=HibernateUtils.getSession();
        String hql="from User u where u.sid=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,sid);
        List<User> users=query.list();
        if(users.size()!=0){
            return users.get(0);
        }
        return null;
    }


}
