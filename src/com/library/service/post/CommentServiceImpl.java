package com.library.service.post;

import com.library.Dao.post.CommentDaoImpl;
import com.library.pojo.Comment;
import com.library.pojo.Praise;
import com.library.pojo.User;

import java.util.Date;
import java.util.List;

public class CommentServiceImpl  implements CommentService{
        @Override
        public List<Comment> showComments(int postId){
            CommentDaoImpl commentDao=new CommentDaoImpl();
            List<Comment> list=commentDao.showComments(postId);
            if(list!=null){
                return list;
            }
            return null;
        }
        @Override
        public void publishComment(User user, String content, Date time, int postId){
            CommentDaoImpl commentDao=new CommentDaoImpl();
            commentDao.publishComment(user,content,time,postId);
        }

        @Override
        public int like(int postId,String userId) {
            CommentDaoImpl commentDao=new CommentDaoImpl();
            List<Praise> list = commentDao.checkLike(postId, userId);
            if(list == null) {
                commentDao.addLike(postId, userId);
                int c=commentDao.like(postId);
                return c;
            }
            return 0;
        }

        @Override
        public int dislike(int postId,String userId) {
            CommentDaoImpl commentDao=new CommentDaoImpl();
            List<Praise> list = commentDao.checkLike(postId, userId);
            if(list != null) {
                commentDao.deleteLike(postId, userId);
                int c=commentDao.dislike(postId);
                return c;
            }
            return 0;
        }
        @Override
        public List<Praise> checkPraise() {
            CommentDaoImpl commentDao=new CommentDaoImpl();
            List<Praise> list = commentDao.checkLike();
            if(list!=null){
                return list;
            }
            return null;
        }

    }
