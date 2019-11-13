package com.xmmufan.permission.controller

import com.xmmufan.permission.domain.rbac.Permission
import com.xmmufan.permission.domain.rbac.PermissionResource
import com.xmmufan.permission.service.PermissionService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

/**
 * @author 黄源钦
 * @Description
 * @Date 15:01
 */
@RestController
@RequestMapping(value = ["/permission"])
open class PermissionController @Autowired constructor( private val permissionService: PermissionService) {

    @GetMapping(value = ["/list"])
    fun queryAllPermission(): List<Permission> {
        return permissionService.queryAllPermission()
    }

    @GetMapping(value = ["/resource"])
    fun queryAllResource(): List<PermissionResource> {
        return permissionService.queryAllResource()
    }

    @PostMapping
    fun createPermission(@RequestBody @Valid permission: Permission) {
        permissionService.createPermission(permission)
    }

    @DeleteMapping(value = ["/{permissionId}"])
    fun deletePermission(@PathVariable permissionId: String) {
        permissionService.deletePermission(permissionId)
    }

    @PostMapping("/modify")
    fun modifyPermission(@RequestBody @Valid permission: Permission) {
        permissionService.modifyPermission(permission)
    }


}
