package com.xmmufan.permission.service


import com.xmmufan.permission.domain.rbac.User
import com.xmmufan.permission.domain.rbac.UserAccount
import com.xmmufan.permission.domain.rbac.UserInfo

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 *
 *
 *
 */
interface UserService {

    fun saveUser(user: User): User

    fun updateUser(user: User): User

    fun queryById(id: String): User

    fun queryAllUser(): List<User>

    fun queryByAccount(account: UserAccount): User

    fun login(account: UserAccount): User

}
