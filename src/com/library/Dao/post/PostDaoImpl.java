package com.library.Dao.post;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.library.pojo.Post;
import com.library.pojo.User;
import com.library.utils.HibernateUtils;

public class PostDaoImpl implements PostDao {

    /**
     * 发表帖子
     */
    @Override
    public void publishPost(String context, User user, String title,String book,Date date) {
        Session session = null;
        session = HibernateUtils.getSession();
        session.beginTransaction();
        Post post = new Post();
        post.setContent(context);
        post.setUser(user.getSid());
        post.setUserName(user.getName());
        post.setTime(date);
        post.setGood(0);
        post.setProfile(user.getProfile());
        post.setTitle(title);
        post.setBook(book);
        session.save(post);
        session.getTransaction().commit();



    }

    /**
     * 帖子列表
     */
    @Override
    public List<Post> listPost() {
        Session session = null;
        session = HibernateUtils.getSession();
        String hql = "from Post p order by p.pid desc ";
        Query query = session.createQuery(hql);
        List<Post> list = query.list();
        if (list.size() != 0) {
            return list;
        }
        return null;
    }

    @Override
    public Post findPost(int postId){
        Session session=HibernateUtils.getSession();
        String hql="from Post p where p.pid=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,postId);
        List<Post> list=query.list();
        if(list.size()!=0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Post> findPost(String book) {
        Session session=HibernateUtils.getSession();
        String hql="from Post p where p.book like '%"+book+"%'";
        Query query=session.createQuery(hql);
        List<Post> list=query.list();
        if(list.size()!=0){
            return list;
        }
        return null;
    }


}
