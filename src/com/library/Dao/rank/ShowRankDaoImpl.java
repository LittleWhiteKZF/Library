package com.library.Dao.rank;

import com.library.pojo.User;
import com.library.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ShowRankDaoImpl implements ShowRankDao{
    @Override
    public List<User> showRank() {
        String hql="from User u order by time desc";
        Session session=HibernateUtils.getSession();
        Query query=session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(20);
        List<User> list=query.list();
        if(list.size()!=0){
            return list;
        }
        return null;
    }
}
