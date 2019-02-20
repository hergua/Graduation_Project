package com.xmmufan.permission.service;

import com.xmmufan.permission.domain.permission.SysRole;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/15
 * <p>
 *     用户角色接口
 * </p>
 */
public interface SysRoleService {

    SysRole findByRoleName(String roleName);

    List<SysRole> findAllRoles();

    SysRole saveOrUpdateRole(SysRole role);

}
