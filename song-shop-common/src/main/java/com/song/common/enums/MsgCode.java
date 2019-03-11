package com.song.common.enums;

/**
 * created on 2019/3/10 11:03
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */

public enum MsgCode {

    /* 系统信息，以00开头 */
    SYS_OK("00000", "操作成功"),
    SYS_INVALID_LOGIN("00001", "登录已失效，请重新登录"),
    SYS_ACCOUNT_ERROR("00002", "账号/密码错误"),
    SYS_USER_IS_EXIT("00003", "账号已经注册，请检查或使用其他账号注册！"),
    SYS_SERVICE_ERROR("00999", "服务器忙，请稍后"),
    SYS_PASSWORD_NOT_NULL("00004", "密码不能为空!"),
    SYS_USER_NAME_NOT_NULL("00005", "用户名不能为空!"),
    SYS_REGISTER_FAIL("00006", "注册失败，请重新注册!"),
    SYS_PHONE_NOT_NULL("00007", "手机号不能为空!"),
    SYS_TOKEN_NOT_NULL("00008", "token不能为空!"),
    SYS_QQ_IS_FAIL("00009", "QQ授权登录失败!"),
    SYS_USER_IS_NOT_EXIT("00009", "没有关联的用户!");


    String code;
    String message;

    MsgCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
