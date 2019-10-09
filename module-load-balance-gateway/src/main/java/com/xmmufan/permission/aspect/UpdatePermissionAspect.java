package com.xmmufan.permission.aspect;

import com.xmmufan.permission.configuration.permission.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/24
 * <p>
 * </p>
 */

@Aspect
@Component
@Slf4j
public class UpdatePermissionAspect {

    private final ShiroService shiroService;

    @Autowired
    public UpdatePermissionAspect(ShiroService shiroService) {
        this.shiroService = shiroService;
    }

    @Pointcut("@annotation(updatePermissionCache)")
    public void pointcut(UpdatePermissionCache updatePermissionCache) {
    }

    @After(value = "pointcut(updatePermissionCache)", argNames = "joinPoint, updatePermissionCache")
    public void updatePermissionCache(JoinPoint joinPoint, UpdatePermissionCache updatePermissionCache){
        shiroService.updatePermission();
    }

}
