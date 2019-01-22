package com.library.service.changePwd;

import com.library.Dao.changePwd.ChangePwdDaoImpl;

public class ChangePwdSerImpl implements ChangePwdService {
    @Override
    public int changePwd( String newPwd) {
        ChangePwdDaoImpl changePwdDao=new ChangePwdDaoImpl();
        int c=changePwdDao.changePwd(newPwd);
        return c;
    }
    @Override
    public int changeAdminPwd(String newPwd){
        ChangePwdDaoImpl changePwdDao=new ChangePwdDaoImpl();
        return  changePwdDao.changeAdminPwd(newPwd);

    }

    @Override
    public String searchPwd(String id) {
        ChangePwdDaoImpl changePwdDao=new ChangePwdDaoImpl();
        return changePwdDao.searchPwd(id);
    }

    @Override
    public String searchAdminPwd(String id) {
        ChangePwdDaoImpl changePwdDao=new ChangePwdDaoImpl();
        return changePwdDao.searchAdminPwd(id);
    }
}
