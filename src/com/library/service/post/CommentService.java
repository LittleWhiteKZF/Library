package com.library.service.post;

import com.library.pojo.Comment;
import com.library.pojo.Praise;
import com.library.pojo.User;

import java.util.Date;
import java.util.List;

public interface CommentService {
    List<Comment> showComments(int postId);
    void publishComment(User user, String content, Date time, int postId);
    int like(int postId,String userId);
    int dislike(int postId,String userId);
    List<Praise> checkPraise();
}
