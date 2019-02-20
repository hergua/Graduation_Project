package com.xmmufan.permission.constant.exception;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/14
 * <p>
 *     自定义用户账户信息异常类
 *     调用情景：用户名或密码缺失
 * </p>
 */
public class AccountInfoMissingException extends Exception {

    public AccountInfoMissingException(String message) {
        super(message);
    }
}
