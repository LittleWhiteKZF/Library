package com.library.Dao.post;

import com.library.pojo.Comment;
import com.library.pojo.Praise;
import com.library.pojo.User;

import java.util.Date;
import java.util.List;

public interface CommentDao {
    List<Comment> showComments(int postId);
    void publishComment(User user, String content, Date time, int postId);
    int like(int postId);
    int dislike(int postId);
    void addLike(int postId,String userId);
    void deleteLike(int postId,String userId);
    List<Praise> checkLike(int postid,String userid);
    List<Praise> checkLike();

}
