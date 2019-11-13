package com.xmmufan.permission.aspect

import com.xmmufan.permission.configuration.permission.ShiroService
import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/24
 *
 *
 *
 */

@Aspect
@Component
@Slf4j
class UpdatePermissionAspect(@Autowired private val shiroService: ShiroService) {

    @Pointcut("@annotation(updatePermissionCache)")
    fun pointcut(updatePermissionCache: UpdatePermissionCache) {
    }

    @After(value = "pointcut(updatePermissionCache)", argNames = "joinPoint, updatePermissionCache")
    fun updatePermissionCache(joinPoint: JoinPoint, updatePermissionCache: UpdatePermissionCache) {
        shiroService.updatePermission()
    }

}
