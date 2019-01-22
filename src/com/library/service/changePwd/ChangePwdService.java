package com.library.service.changePwd;

public interface ChangePwdService {
    int changePwd(String newPwd);
    int changeAdminPwd(String newPwd);
    String searchPwd(String id);
    String searchAdminPwd(String id);
}
