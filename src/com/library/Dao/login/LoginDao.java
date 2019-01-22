package com.library.Dao.login;

import com.library.pojo.Administrator;
import com.library.pojo.User;

public interface LoginDao {
    User login(String sid,String password);
    Administrator adminLogin(String adminId,String password);
    User flushtudent(String sid);
}
