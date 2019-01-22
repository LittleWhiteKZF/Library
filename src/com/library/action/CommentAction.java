package com.library.action;



import com.library.pojo.User;
import com.library.service.post.CommentServiceImpl;

import com.opensymphony.xwork2.ActionContext;


import java.util.Date;

public class CommentAction {
    private String content;
    private int pid;
    private User user = (User) ActionContext.getContext().getSession().get("user");

    public String publishComment() {
        CommentServiceImpl commentService = new CommentServiceImpl();

        commentService.publishComment(user, content, new Date(), pid);
        ActionContext.getContext().getSession().put("find", null);
        ActionContext.getContext().getSession().put("change",null);
        return "success";
    }



    public String like() {
        CommentServiceImpl commentService = new CommentServiceImpl();
        int c = commentService.like(pid,user.getSid());
        return "success";
    }

    public String dislike() {
        CommentServiceImpl commentService = new CommentServiceImpl();
        User user = (User) ActionContext.getContext().getSession().get("user");
        int c = commentService.dislike(pid,user.getSid());
        return "success";
    }







    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

