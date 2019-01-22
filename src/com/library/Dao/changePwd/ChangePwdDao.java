package com.library.Dao.changePwd;

public interface ChangePwdDao {
    int changePwd(String newPwd);
    int changeAdminPwd(String newPwd);
    String searchPwd(String id);
    String searchAdminPwd(String id);
}
