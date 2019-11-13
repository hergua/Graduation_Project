package com.xmmufan.permission.repository

import com.xmmufan.permission.domain.rbac.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/12
 *
 *
 *
 */

@Repository
interface UserRepository : JpaRepository<User, String> {

    fun queryByAccount_Id(accountId: String): User

    fun queryByAccount_Username(username: String): User
}
