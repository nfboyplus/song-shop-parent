package com.song.common.constants;

/**
 * created on 2019/3/9 11:03
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
public interface Constants {

    //用户会话保存90天
    Long USER_TOKEN_TERM_VALIDITY = 60 * 60 * 24 * 90L;
    int WEBUSER_COOKIE_TOKEN_TERMVALIDITY = 1000 * 60 * 60 * 24 * 90;
    String USER_TOKEN = "token";
    String USER_SESSION_OPENID= "openId";
    String USER_SOURCE_QQ = "QQ";



}
