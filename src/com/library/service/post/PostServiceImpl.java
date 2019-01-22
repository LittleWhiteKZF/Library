package com.library.service.post;

import java.util.Date;
import java.util.List;

import com.library.Dao.post.PostDaoImpl;

import com.library.pojo.Post;
import com.library.pojo.User;
import javafx.geometry.Pos;

public class PostServiceImpl implements PostService {

    @Override
    public void publishPost(String context, User user, String title, String book, Date date) {
        PostDaoImpl postDao = new PostDaoImpl();
        postDao.publishPost(context, user, title, book, date);
    }

    @Override
    public List<Post> listPost() {
        PostDaoImpl postDao = new PostDaoImpl();
        List<Post> list = postDao.listPost();

        return list;
    }

    @Override
    public List<Post> findPost(String book) {
        PostDaoImpl postDao = new PostDaoImpl();
        List<Post> list=postDao.findPost(book);
        return list;
    }


}
