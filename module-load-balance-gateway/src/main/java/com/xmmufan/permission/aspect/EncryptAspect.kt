package com.xmmufan.permission.aspect

import com.xmmufan.permission.constant.exception.AccountException
import com.xmmufan.permission.domain.rbac.UserAccount
import org.apache.commons.lang3.StringUtils
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.SimpleHash
import org.apache.shiro.util.ByteSource
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/14
 *
 *
 * 注解切面类
 *
 */

@Aspect
@Component
class EncryptAspect {

    @Pointcut("@annotation(encryptedPassword)")
    fun mark(encryptedPassword: EncryptedPasswordAndSalt) {}

    /**
     * @Description: 切面自动获取参数User 自动加密password 若salt不存在也进行设置
     * @Param: [joinPoint, encryptedPassword]
     * @return: void
     * @Author: Mr.Hergua
     * @Date: 2018/11/17
     */
    @Before(value = "mark(encryptedPassword)", argNames = "joinPoint,encryptedPassword")
    @Throws(Throwable::class)
    fun doBefore(joinPoint: JoinPoint, encryptedPassword: EncryptedPasswordAndSalt) {

        try {
            for (userAccount in joinPoint.args) {
                if (userAccount is UserAccount) {
                    val userPassword = userAccount.javaClass.getDeclaredField("password")
                    val userSalt = userAccount.javaClass.getDeclaredField("salt")
                    userPassword.isAccessible = true
                    userSalt.isAccessible = true
                    if (StringUtils.isBlank(userSalt.get(userAccount) as String?)) {
                        userSalt.set(userAccount,
                                SecureRandomNumberGenerator().nextBytes().toHex())
                    }
                    userPassword.set(userAccount,
                            SimpleHash(encryptedPassword.algorithm,
                                    userPassword.get(userAccount),
                                    ByteSource.Util.bytes(userSalt.get(userAccount) as String),
                                    encryptedPassword.hashIterations).toString())
                }
            }
        } catch (e: Exception) {
            log.error("Do encrypt method occupied an error: ", e)
            throw AccountException("There is no object contains field 'password'. ")
        }
    }

    companion object{
        val log: Logger = LoggerFactory.getLogger(EncryptAspect::class.java)
    }

}
