package com.xmmufan.permission.service

import com.xmmufan.permission.domain.rbac.UserGroup

/**
 * @author 黄源钦
 * @Description
 * @Date 16:13
 */
interface UserGroupService {

    fun createUserGroup(group: UserGroup)

    fun disableUserGroup(groupId: String)

    fun enableUserGroup(groupId: String)

    fun updatePermission(groupId: String, modifyIds: List<String>)

    fun updateGroupUser(groupId: String, userIds: List<String>)

}
