package com.library.Dao.post;

import com.library.pojo.Comment;
import com.library.pojo.Praise;
import com.library.pojo.User;
import com.library.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;







public class CommentDaoImpl implements CommentDao {
    //        显示书评的评论
    @Override
    public List<Comment> showComments(int postId){
        Session session=HibernateUtils.getSession();
        String hql="from Comment c where c.postId=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,postId);
        List<Comment> list=query.list();
        if(list.size()!=0){
            return list;
        }
        return null;
    }

    //    发表评论
    @Override
    public void publishComment(User user, String content, Date time,int postId){
        Session session=HibernateUtils.getSession();
        session.beginTransaction();
        Comment comment=new Comment();
        comment.setContent(content);
        comment.setPostId(postId);
        comment.setUserId(user.getSid());
        comment.setUserName(user.getName());
        comment.setProfile(user.getProfile());
        comment.setTime(time);
        session.save(comment);
        session.getTransaction().commit();
    }
    @Override
    public int like(int postId){
        Session session=HibernateUtils.getSession();
        Transaction trans=session.beginTransaction();
        String hql="update Post p set p.good=p.good+1 where pid=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,postId);
        int c = query.executeUpdate();
        trans.commit();
        return c;
    }
    @Override
    public int dislike(int postId){
        Session session=HibernateUtils.getSession();
        Transaction trans=session.beginTransaction();
        String hql="update Post p set p.good=p.good-1 where pid=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,postId);
        int c=query.executeUpdate();
        trans.commit();
        return c;
    }

    @Override
    public void addLike(int postId, String userId) {
        Session session=HibernateUtils.getSession();
        session.beginTransaction();
        Praise praise=new Praise();
        praise.setPostid(postId);
        praise.setUserid(userId);
        praise.setTime(new Date());
        session.save(praise);
        session.getTransaction().commit();
    }

    @Override
    public List<Praise> checkLike(int postid, String userid) {
        Session session=HibernateUtils.getSession();
        String hql="from Praise p where p.userid=? and p.postid=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,userid);
        query.setParameter(1,postid);
        List<Praise> list=query.list();
        if(list.size()!=0){
            return list;
        }
        return null;
    }

    @Override
    public List<Praise> checkLike() {
        Session session=HibernateUtils.getSession();
        String hql="from Praise";
        Query query=session.createQuery(hql);
        List<Praise> list=query.list();
        if(list.size()!=0){
            return list;
        }
        return null;
    }

    @Override
    public void deleteLike(int postId, String userId) {
        Session session=HibernateUtils.getSession();
        Transaction trans=session.beginTransaction();
        String hql="Delete from Praise p where p.userid=? and p.postid=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,postId);
        query.executeUpdate();
        trans.commit();
    }

}
