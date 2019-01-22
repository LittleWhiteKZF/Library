package com.library.pojo;

public class User {
    private String sid;
    private String password;
    private String name;
    private String profile;
    private int credit;
    private double time;
    private String major;

    public User() {
    }

    public User(String sid, String password, String name, String profile,
                int credit, double time, String major) {
        this.sid = sid;
        this.password = password;
        this.name = name;
        this.profile = profile;
        this.credit = credit;
        this.time = time;
        this.major = major;
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
