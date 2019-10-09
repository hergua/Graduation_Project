package com.xmmufan.permission.service;


import com.xmmufan.permission.domain.rbac.Permission;
import com.xmmufan.permission.domain.rbac.PermissionResource;

import java.util.List;

/**
 * @author 黄源钦
 * @Description
 * @Date 16:23
 */
public interface PermissionService {

    void createPermission(Permission permission);

    void modifyPermission(Permission permission);

    void disablePermission(String id);

    void enablePermission(String id);

    void modifyAtomResource(String id, List<PermissionResource> atomResources);

    List<PermissionResource> queryAllPermissionResource();


}
