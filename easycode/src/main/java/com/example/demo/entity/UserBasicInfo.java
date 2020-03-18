package com.example.demo.entity;

import java.io.Serializable;

/**
 * (UserBasicInfo)实体类
 *
 * @author makejava
 * @since 2020-02-16 20:10:45
 */
public class UserBasicInfo implements Serializable {
    private static final long serialVersionUID = 644352387534173361L;
    
    private String openId;
    
    private String userName;
    
    private String userCity;
    
    private String userPhone;
    
    private String userIcon;
    
    private Object userGrade;
    
    private Object userSex;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Object getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(Object userGrade) {
        this.userGrade = userGrade;
    }

    public Object getUserSex() {
        return userSex;
    }

    public void setUserSex(Object userSex) {
        this.userSex = userSex;
    }

}