package com.library.Dao.post;

import java.util.Date;
import java.util.List;

import com.library.pojo.Post;
import com.library.pojo.User;

public interface PostDao {

    void publishPost(String context, User user, String title, String book, Date date);   //发表帖子

    List<Post> listPost();    //查看帖子

    Post findPost(int postId);

    List<Post> findPost(String book);
}
