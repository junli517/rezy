package com.rezy.dialog.model.entity;
import java.io.Serializable;

/**
 * @Description :
 * @version 1.0
 * @Author jun.li
 * @Date 2019-03-27
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //三方登录id
    private Integer appUserId;
    //三方登录类型：1-QQ、2-微信
    private Integer appType;
    //用户token
    private String userToken;
    /**
     * 获取主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取三方登录id
     */
    public Integer getAppUserId() {
        return appUserId;
    }

    /**
     * 设置三方登录id
     */
    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    /**
     * 获取三方登录类型：1-QQ、2-微信
     */
    public Integer getAppType() {
        return appType;
    }

    /**
     * 设置三方登录类型：1-QQ、2-微信
     */
    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    /**
     * 获取用户token
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * 设置用户token
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}