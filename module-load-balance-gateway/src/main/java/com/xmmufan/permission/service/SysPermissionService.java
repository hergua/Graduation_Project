package com.xmmufan.permission.service;

import com.xmmufan.permission.domain.permission.SysPermission;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/17
 * <p>
 * </p>
 */
public interface SysPermissionService {

    List<SysPermission> findAllPermissions();

    SysPermission savePermission(SysPermission sysPermission);

}
