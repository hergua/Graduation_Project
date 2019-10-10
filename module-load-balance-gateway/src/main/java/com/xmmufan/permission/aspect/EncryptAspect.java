package com.xmmufan.permission.aspect;

import com.xmmufan.permission.algorithm.SnowFlake;
import com.xmmufan.permission.constant.exception.AccountException;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/14
 * <p>
 * 注解切面类
 * </p>
 */

@Aspect
@Component
@Slf4j
public class EncryptAspect {

    @Pointcut("@annotation(encryptedPassword)")
    public void mark(EncryptedPasswordAndSalt encryptedPassword) {
    }

    /**
     * @Description: 切面自动获取参数User 自动加密password 若salt不存在也进行设置
     * @Param: [joinPoint, encryptedPassword]
     * @return: void
     * @Author: Mr.Hergua
     * @Date: 2018/11/17
     */
    @Before(value = "mark(encryptedPassword)", argNames = "joinPoint,encryptedPassword")
    public void doBefore(JoinPoint joinPoint, EncryptedPasswordAndSalt encryptedPassword) throws Throwable {

        Object[] params = joinPoint.getArgs();
        try {
            for (Object userAccount : params) {
                if (userAccount instanceof UserAccount) {
                    Field userPassword = userAccount.getClass().getDeclaredField("password");
                    Field userSalt = userAccount.getClass().getDeclaredField("salt");
                    userPassword.setAccessible(true);
                    userSalt.setAccessible(true);
                    if (StringUtils.isBlank((String) userAccount.getClass().getDeclaredMethod("getSalt").invoke(userAccount))) {
                        userSalt.set(userAccount, new SecureRandomNumberGenerator().nextBytes().toHex());
                    }
                    userPassword.set(userAccount, new SimpleHash(encryptedPassword.algorithm(),
                            userPassword.get(userAccount), ByteSource.Util.bytes(userSalt.get(userAccount)),
                            encryptedPassword.hashIterations()).toString());
                }
            }
        } catch (Exception e) {
            log.error("Do encrypt method occupied an error: ", e);
            throw new AccountException("There is no object contains field 'password'. ");
        }
    }

}
