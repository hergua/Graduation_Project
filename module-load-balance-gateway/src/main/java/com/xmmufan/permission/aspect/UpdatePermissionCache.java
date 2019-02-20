package com.xmmufan.permission.aspect;

import java.lang.annotation.*;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/24
 * <p>
 *     更新权限缓存注解
 * </p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdatePermissionCache {
}
