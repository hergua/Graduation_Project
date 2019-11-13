package com.xmmufan.permission.service

import com.google.protobuf.ServiceException
import com.xmmufan.permission.constant.exception.AccountException
import com.xmmufan.permission.domain.rbac.User
import com.xmmufan.permission.domain.rbac.UserAccount

/**
 * @author 黄源钦
 * @Description 用户账号接口
 * @Date 15:43
 */
interface UserAccountService {

    @Throws(AccountException::class)
    fun updatePassword(id: String, password: String)

    @Throws(AccountException::class, ServiceException::class)
    fun lockAccount(id: String)

    @Throws(AccountException::class)
    fun unlockAccount(id: String)

    fun findByUsername(username: String): UserAccount?

    @Throws(AccountException::class)
    fun saveUserAccount(account: UserAccount)

}
