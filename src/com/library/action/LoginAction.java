package com.library.action;

import com.library.pojo.Administrator;
import com.library.pojo.User;
import com.library.service.login.LoginServiceImpl;
import com.library.service.rank.ShowRankServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Random;

public class LoginAction extends ActionSupport {
    private String sid;//用户输入的ID
    private String password;//用户输入的密码
    private User user;//学生用户
    private Administrator administrator;//管理员
    private String identity;//身份


    public void flushStudent(){//每次刷新都重新获取数据库中学生信息
        LoginServiceImpl loginService=new LoginServiceImpl();
        user= (User) ActionContext.getContext().getSession().get("user");
        user=loginService.flushStudent(user.getSid());
        ActionContext.getContext().getSession().put("user",user);
    }


    @Override
    public String execute() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        LoginServiceImpl loginService =new LoginServiceImpl();

        if(identity.equals("student")) {//若登录身份为学生则转到学生页面
            user = loginService.login(sid, password);

            if (user == null) {
                return ERROR;
            }
            ActionContext.getContext().getSession().put("user", user);//保存当前用户
            ShowRankServiceImpl showRankService = new ShowRankServiceImpl();//获取排名
            List<User> list = showRankService.showRank();
            ActionContext.getContext().getSession().put("rank", list);//保存当前排名
            return SUCCESS;
        }else {//登录身份为管理员则进入管理员界面
            administrator=loginService.adminLogin(sid,password);
            if(administrator==null){
                return ERROR;
            }
            ActionContext.getContext().getSession().put("admin",administrator);
            ShowRankServiceImpl showRankService = new ShowRankServiceImpl();
            List<User> list = showRankService.showRank();
            ActionContext.getContext().getSession().put("rank", list);
            return "admin";
        }
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
