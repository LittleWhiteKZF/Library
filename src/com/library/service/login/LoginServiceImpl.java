package com.library.service.login;

import com.library.Dao.login.LoginDaoImpl;
import com.library.pojo.Administrator;
import com.library.pojo.User;

public class LoginServiceImpl implements LoginService {
    @Override
    public User login(String sid, String password) {
        LoginDaoImpl loginDao = new LoginDaoImpl();
        User user = loginDao.login(sid, password);
        return user;
    }

    @Override
    public Administrator adminLogin(String adminId, String password) {
        LoginDaoImpl loginDao = new LoginDaoImpl();
        Administrator administrator = loginDao.adminLogin(adminId, password);
        return administrator;
    }

    @Override
    public User flushStudent(String sid) {
        LoginDaoImpl loginDao=new LoginDaoImpl();
        User user=loginDao.flushtudent(sid);
        return user;
    }
}
