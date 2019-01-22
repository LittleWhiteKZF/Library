package com.library.service.post;

import java.util.Date;
import java.util.List;

import com.library.pojo.Post;
import com.library.pojo.User;

public interface PostService {

    void publishPost(String context, User user, String title, String book, Date date);   //发表帖子

    List<Post> listPost();    //查看帖子

    List<Post> findPost(String book);
}
