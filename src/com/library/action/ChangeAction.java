package com.library.action;

import com.library.pojo.Administrator;
import com.library.pojo.User;
import com.library.service.changePwd.ChangePwdSerImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class ChangeAction extends ActionSupport {
    private String oldPwd;
    private String newPwd;

    public String changeAdmin(){//修改管理员密码
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ChangePwdSerImpl changePwdSer=new ChangePwdSerImpl();
        Administrator administrator= (Administrator) ActionContext.getContext().getSession().get("admin");
        String oPassword=changePwdSer.searchAdminPwd(administrator.getAdminId());
        Map session=ActionContext.getContext().getSession();
        if(oPassword.equals(oldPwd)){
            int c=changePwdSer.changeAdminPwd(newPwd);
            if(c>0){
                return SUCCESS;
            }else  {
                session.put("msg","密码修改异常！");
                return  ERROR;
            }

        }
        session.put("msg","旧密码输入错误！");

        return ERROR;
    }


    @Override
    public String execute() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ChangePwdSerImpl changePwdSer=new ChangePwdSerImpl();
        User user= (User) ActionContext.getContext().getSession().get("user");//获得当前登录的用户
        String oPassword=changePwdSer.searchPwd(user.getSid());
        Map session=ActionContext.getContext().getSession();
        if(oPassword.equals(oldPwd)){
            int c=changePwdSer.changePwd(newPwd);
            if(c>0){
                return SUCCESS;
            }else  {
                session.put("msg","密码修改异常！");
                return  ERROR;
            }

        }
        session.put("msg","旧密码输入错误！");

        return ERROR;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
