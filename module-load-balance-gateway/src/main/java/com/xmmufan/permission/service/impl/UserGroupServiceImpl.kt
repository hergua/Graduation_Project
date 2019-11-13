package com.xmmufan.permission.service.impl

import com.xmmufan.permission.domain.rbac.UserGroup
import com.xmmufan.permission.repository.PermissionRepository
import com.xmmufan.permission.repository.UserGroupRepository
import com.xmmufan.permission.repository.UserRepository
import com.xmmufan.permission.service.UserGroupService
import org.springframework.stereotype.Service

import javax.transaction.Transactional

/**
 * @author 黄源钦
 * @Description
 * @Date 16:37
 */
@Service
open class UserGroupServiceImpl(private val userRepository: UserRepository, private val groupRepository: UserGroupRepository, private val permissionRepository: PermissionRepository) : UserGroupService {

    override fun createUserGroup(group: UserGroup) {
        groupRepository.save(group)
    }


    override fun disableUserGroup(groupId: String) {
        assert(false) { "此功能未开放" }
    }

    override fun enableUserGroup(groupId: String) {
        assert(false) { "此功能未开放" }
    }


    @Transactional
    override fun updatePermission(groupId: String, modifyIds: List<String>) {
        groupRepository.saveAndFlush(groupRepository.findById(groupId).get().apply {
            this.basicPermissions = permissionRepository.findAllById(modifyIds)
        })
    }

    @Transactional
    override fun updateGroupUser(groupId: String, userIds: List<String>) {
        val targetUsers = userRepository.findAllById(userIds)
        val targetGroup = groupRepository.findById(groupId).get()
        groupRepository.saveAndFlush(targetGroup.apply { this.users = targetUsers })
    }
}
