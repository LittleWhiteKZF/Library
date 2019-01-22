package com.library.pojo;

import java.util.Date;

public class Administrator {
    private String adminId;
    private String password;
    private String name;
    private String profile;
    private Date intime;

    public Administrator() {
    }

    public Administrator(String adminId, String password, String name, String profile,Date intime) {
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.profile = profile;
        this.intime=intime;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }
}
