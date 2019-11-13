package com.xmmufan.permission.service


import com.xmmufan.permission.domain.rbac.UserInfo

interface UserInfoService {

    fun saveOrUpdate(userInfo: UserInfo, userId: String)

    fun queryByUser(userId: String): UserInfo?

    fun queryById(id: String): UserInfo?

}
