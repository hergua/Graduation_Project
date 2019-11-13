package com.xmmufan.permission.domain.vo

import com.xmmufan.permission.domain.rbac.User
import com.xmmufan.permission.domain.rbac.UserGroup
import com.xmmufan.permission.domain.rbac.UserInfo

import java.util.*

/**
 * @author 黄源钦
 * @Description
 * @Date 13:24
 */
data class UserInfoVo(val user: User) {

    private var userGroup: UserGroup? = null

    private var userInfo: UserInfo? = null

    private var permissions: Map<String, String> ? = HashMap(50)

    init {
        this.userInfo = user.info
        this.userGroup = user.userGroup
        this.permissions = computePermissionStrMap(user)
    }

    /**
     * @author huangyq
     * @Description 把用户下的特别权限，以及用户所属的用户组的权限进行收集
     * @Date 2019/10/11 15:48
     * @param user 用户
     * @return 权限描述map
     */
    private fun computePermissionStrMap(user: User): Map<String, String> {
        return HashMap<String, String>(50).also {

            user.customizePermission?.forEach{ permission ->
                permission.resources?.forEach { resource ->
                    it[resource.url ?: ""] =  resource.operation ?: "" }}

            user.userGroup?.basicPermissions?.forEach { permission->
                permission.resources?.forEach { resource ->
                    it[resource.url ?: ""] = resource.operation ?: "" } }
        }
    }

}
