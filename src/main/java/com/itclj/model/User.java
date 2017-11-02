package com.itclj.model;

import java.util.Date;

public class User {
    private Integer uid;

    private String uname;

    private String upassword;

    private String upersoname;

    private String unum;

    private String urole;

    private String uisdel;

    private Date uctime;

    private String uisadmin;

    private Date ri;

    private Date ri2;

    private Date ri3;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUpersoname() {
        return upersoname;
    }

    public void setUpersoname(String upersoname) {
        this.upersoname = upersoname;
    }

    public String getUnum() {
        return unum;
    }

    public void setUnum(String unum) {
        this.unum = unum;
    }

    public String getUrole() {
        return urole;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }

    public String getUisdel() {
        return uisdel;
    }

    public void setUisdel(String uisdel) {
        this.uisdel = uisdel;
    }

    public Date getUctime() {
        return uctime;
    }

    public void setUctime(Date uctime) {
        this.uctime = uctime;
    }

    public String getUisadmin() {
        return uisadmin;
    }

    public void setUisadmin(String uisadmin) {
        this.uisadmin = uisadmin;
    }

    public Date getRi() {
        return ri;
    }

    public void setRi(Date ri) {
        this.ri = ri;
    }

    public Date getRi2() {
        return ri2;
    }

    public void setRi2(Date ri2) {
        this.ri2 = ri2;
    }

    public Date getRi3() {
        return ri3;
    }

    public void setRi3(Date ri3) {
        this.ri3 = ri3;
    }
}