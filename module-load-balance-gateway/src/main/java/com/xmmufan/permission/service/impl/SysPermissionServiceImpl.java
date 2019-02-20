package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.domain.permission.SysPermission;
import com.xmmufan.permission.repository.SysPermissionRepository;
import com.xmmufan.permission.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/17
 * <p>
 * </p>
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private final SysPermissionRepository permissionRepository;

    @Autowired
    public SysPermissionServiceImpl(SysPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<SysPermission> findAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public SysPermission savePermission(SysPermission sysPermission) {
        return permissionRepository.save(sysPermission);
    }
}
