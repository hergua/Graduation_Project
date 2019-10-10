package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.domain.rbac.Permission;
import com.xmmufan.permission.domain.rbac.PermissionResource;
import com.xmmufan.permission.repository.PermissionRepository;
import com.xmmufan.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 黄源钦
 * @Description
 * @Date 16:37
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void createPermission(Permission permission) {

    }

    @Override
    public void modifyPermission(Permission permission) {

    }

    @Override
    public void disablePermission(String id) {

    }

    @Override
    public void enablePermission(String id) {

    }

    @Override
    public void modifyAtomResource(String id, List<PermissionResource> atomResources) {

    }

    @Override
    public List<Permission> queryAllPermission() {
        return  permissionRepository.findAll();
    }
}
