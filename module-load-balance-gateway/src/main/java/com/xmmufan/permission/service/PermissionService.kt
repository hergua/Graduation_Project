package com.xmmufan.permission.service


import com.xmmufan.permission.domain.rbac.Permission
import com.xmmufan.permission.domain.rbac.PermissionResource

/**
 * @author 黄源钦
 * @Description
 * @Date 16:23
 */
interface PermissionService {

    fun createPermission(permission: Permission)

    fun modifyPermission(permission: Permission)

    fun disablePermission(id: String)

    fun enablePermission(id: String)

    fun modifyAtomResource(id: String, atomResources: List<PermissionResource>)

    fun queryAllPermission(): List<Permission>

    fun queryAllResource(): List<PermissionResource>

    fun deletePermission(id: String)


}
