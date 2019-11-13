package com.xmmufan.permission.controller

import com.google.protobuf.ServiceException
import com.xmmufan.permission.domain.rbac.UserGroup
import com.xmmufan.permission.service.UserGroupService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @author 黄源钦
 * @Description
 * @Date 15:56
 */
@RestController
@RequestMapping(value = ["/userGroup"])
open class UserGroupController @Autowired constructor(private val userGroupService: UserGroupService) {

    @PostMapping(value = ["/saveGroup"])
    fun saveGroup(@RequestBody @Valid group: UserGroup) {
        userGroupService.createUserGroup(group)
    }

    @PostMapping(value = ["/updateUser"])
    fun updateUser(@NotNull groupId: String, @NotNull userIds: List<String>) {
        userGroupService.updateGroupUser(groupId, userIds)
    }


    @PostMapping(value = ["/permission/grant/{groupId}"])
    fun grantPermission(@RequestBody permissionIds: List<String>, @PathVariable groupId: String) {
        userGroupService.updatePermission(groupId, permissionIds)
    }

}
