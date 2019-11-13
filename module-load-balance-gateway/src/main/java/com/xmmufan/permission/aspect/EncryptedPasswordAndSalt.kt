package com.xmmufan.permission.aspect


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/14
 *
 *
 * 加密注解
 * 加上该注解，在方法执行前进行拦截，对参数内的password与salt进行赋值加密
 * 注：如果参数未包含password与salt字段，则抛出com.xmmufan.project.constant.exception.AccountException
 *
 */

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class EncryptedPasswordAndSalt(//加密方式
        val algorithm: String = "MD5", //加密次数
        val hashIterations: Int = 8)
