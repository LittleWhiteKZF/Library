package com.library.service.login;

import com.library.pojo.Administrator;
import com.library.pojo.User;

public interface LoginService {
    User login(String sid, String password);

    Administrator adminLogin(String adminId, String password);

    User flushStudent(String sid);
}
