package com.xmmufan.permission.aspect;

import java.lang.annotation.*;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/14
 * <p>
 *     加密注解
 *     加上该注解，在方法执行前进行拦截，对参数内的password与salt进行赋值加密
 *     注：如果参数未包含password与salt字段，则抛出com.xmmufan.project.constant.exception.AccountException
 * </p>
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptedPasswordAndSalt {

    //加密方式
    String algorithm() default "MD5";

    //加密次数
    int hashIterations() default 8;

}
