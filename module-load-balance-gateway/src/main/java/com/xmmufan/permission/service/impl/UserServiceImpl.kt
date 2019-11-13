package com.xmmufan.permission.service.impl

import com.xmmufan.permission.domain.rbac.User
import com.xmmufan.permission.domain.rbac.UserAccount
import com.xmmufan.permission.domain.rbac.UserInfo
import com.xmmufan.permission.repository.UserAccountRepository
import com.xmmufan.permission.repository.UserRepository
import com.xmmufan.permission.service.UserService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.subject.Subject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 *
 *
 * 用户服务详细接口类
 *
 */
@Service
class UserServiceImpl
@Autowired constructor(private val userRepository: UserRepository, private val accountRepository: UserAccountRepository) : UserService {

    override fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    override fun updateUser(user: User): User {
        throw RuntimeException("this method shouldn't been call !")
    }

    override fun queryById(id: String): User {
        return userRepository.findById(id).orElse(null)
    }

    override fun queryAllUser(): List<User> {
        return userRepository.findAll()
    }

    override fun queryByAccount(account: UserAccount): User {
        return userRepository.queryByAccount_Id(account.id!!)
    }

    override fun login(account: UserAccount): User {
        val subject = SecurityUtils.getSubject()
        val token = UsernamePasswordToken(account.username, account.password)
        subject.login(token)
        return userRepository.queryByAccount_Username(account.username!!)
    }
}
