package com.health.demo.intelligentfitness.api;

/**
 * Created by Administrator on 2017/4/30.
 */

public class ApiAddress {
    public static String API = "http://112.74.127.99:4000/api/";
    /**
     * 登录
     */
    public static String LOGIN="auth";
    /**
     * 注册
     */
    public static String REGISTER="register";

    /**
     * 忘记密码
     */
    public static String RET_PWD="password/reset";

    /**
     * 列表资讯
     */
    public static String NEWS_LIST="informs";
    /**
     * 修改个人信息
     */
    public static String EDIT_INFO="user-info";
    /**
     * 修改密码
     */
    public static String EDIT_PWD="password/update";
    public static String getURL(String str) {
        String url = API + str;
        return url;
    }
}
