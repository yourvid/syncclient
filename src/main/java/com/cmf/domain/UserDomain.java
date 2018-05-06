package com.cmf.domain;

import javax.persistence.*;

/**
 * Created by yaowei on 2018/5/4.
 */
@Entity
@Table(name = "t_user")
public class UserDomain {
    /** 用户id */
    @Id
    @GeneratedValue
    private long id;
    /** 用户编号 */
    @Column(nullable = true)
    private String userNo;
    /** 用户名称 */
    @Column(nullable = true)
    private String userName;
    /** 用户密码 */
    @Column(nullable = true)
    private String userPwd;
    /** 用户盐值 */
    @Column(nullable = true)
    private String salt;
    /** 用户状态 */
    @Column(nullable = true)
    private String userStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
