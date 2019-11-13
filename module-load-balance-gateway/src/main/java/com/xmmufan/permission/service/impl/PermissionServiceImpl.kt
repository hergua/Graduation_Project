package com.xmmufan.permission.service.impl

import com.xmmufan.permission.controller.UrlMapperController
import com.xmmufan.permission.domain.rbac.Permission
import com.xmmufan.permission.domain.rbac.PermissionResource
import com.xmmufan.permission.repository.PermissionRepository
import com.xmmufan.permission.repository.PermissionResourceRepository
import com.xmmufan.permission.service.PermissionService
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.transaction.Transactional
import java.sql.SQLDataException
import java.sql.SQLException
import kotlin.math.log

/**
 * @author 黄源钦
 * @Description
 * @Date 16:37
 */
@Service
open class PermissionServiceImpl
@Autowired constructor(private val permissionRepository: PermissionRepository,
                       private val resourceRepository: PermissionResourceRepository,
                       private val mapping: UrlMapperController)
    : PermissionService, InitializingBean {

    override fun afterPropertiesSet() {
        mapping.list().forEach{ println(it) }
    }

    @Transactional(rollbackOn = [SQLException::class, SQLDataException::class])
    override fun createPermission(permission: Permission) {
        permissionRepository.save(permission)
    }

    @Transactional(rollbackOn = [SQLException::class, SQLDataException::class])
    override fun modifyPermission(permission: Permission) {
        permissionRepository.saveAndFlush(permission)
    }

    override fun disablePermission(id: String) {
        permissionRepository.deleteById(id)
    }

    override fun enablePermission(id: String) {
        assert(false) {"此功能暂未开放"}
    }

    override fun modifyAtomResource(id: String, atomResources: List<PermissionResource>) {

    }

    override fun queryAllPermission(): List<Permission> {
        return permissionRepository.findAll()
    }

    override fun queryAllResource(): List<PermissionResource> {
        return resourceRepository.findAll()
    }

    override fun deletePermission(id: String) {
        permissionRepository.deleteById(id)
    }
}
