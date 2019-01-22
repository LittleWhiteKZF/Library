package com.library.action;

import com.library.pojo.Comment;
import com.library.pojo.Post;
import com.library.pojo.Praise;
import com.library.pojo.User;
import com.library.service.post.CommentServiceImpl;
import com.library.service.post.PostServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostOpAction extends ActionSupport {
    private String title;
    private String context;
    private String book;//每条书评的书名
    private String book_name_input;//用户查找的书评的书名
    private int pageNum;//总页数
    private int nowPage=1;
    private String result;

    public void showPosts() {

        PostServiceImpl postService = new PostServiceImpl();
        List<Post> list = postService.listPost();
        List<Post> page = new ArrayList<>();
        CommentServiceImpl commentService = new CommentServiceImpl();
        for (Post post : list) {
            List<Comment> comments = commentService.showComments(post.getPid());
            post.setComments(comments);
        }
        List<Praise> praise = commentService.checkPraise();
        if (praise != null) {
            ActionContext.getContext().getSession().put("praise", praise);
        }
        if(list.size()%4==0){
            pageNum=list.size()/4;
        }else {
            pageNum=list.size()/4+1;
        }

        for(int i=(nowPage-1)*4;i<nowPage*4;i++){
            if(i==list.size()){
                break;
            }
            page.add(list.get(i));
        }

        ActionContext.getContext().getSession().put("post", page);
        ActionContext.getContext().getSession().put("pageNum", pageNum);
        ActionContext.getContext().getSession().put("pageNow", nowPage);

    }

    public void changePage(){
        showPosts();
        ActionContext.getContext().getSession().put("change","c");
    }

    public String publishPost() {
        PostServiceImpl postService = new PostServiceImpl();
        User user = (User) ActionContext.getContext().getSession().get("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        sdf.format(date);
        postService.publishPost(context, user, title, book, date);
        ActionContext.getContext().getSession().put("change",null);
        return "success";
    }

    public String findPost() {
        PostServiceImpl postService = new PostServiceImpl();
        List<Post> list = postService.findPost(book_name_input);
        if(list==null){
            return "success";
        }
        List<Post> page = new ArrayList<>();
        if(list.size()%4==0){
            pageNum=list.size()/4;
        }else {
            pageNum=list.size()/4+1;
        }
        for(int i=(nowPage-1)*4;i<nowPage*4;i++){
            if(i==list.size()){
                break;
            }
            page.add(list.get(i));
        }
        ActionContext.getContext().getSession().put("post", page);
        ActionContext.getContext().getSession().put("pageNum", pageNum);
        ActionContext.getContext().getSession().put("pageNow", nowPage);
        ActionContext.getContext().getSession().put("find", "1");
        ActionContext.getContext().getSession().put("change",null);
        return "success";
    }

    public String isNull(){
        PostServiceImpl postService = new PostServiceImpl();
        List<Post> list = postService.findPost(book_name_input);
        if(list==null){
            result="null";
        }
        return "success";
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getBook_name_input() {
        return book_name_input;
    }

    public void setBook_name_input(String book_name_input) {
        this.book_name_input = book_name_input;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
