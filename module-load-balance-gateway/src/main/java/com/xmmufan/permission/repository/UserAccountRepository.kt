package com.xmmufan.permission.repository

import com.xmmufan.permission.domain.rbac.UserAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author 黄源钦
 * @Description
 * @Date 13:40
 */
@Repository
interface UserAccountRepository : JpaRepository<UserAccount, String> {

    fun queryByUsername(username: String): UserAccount?
    
}
