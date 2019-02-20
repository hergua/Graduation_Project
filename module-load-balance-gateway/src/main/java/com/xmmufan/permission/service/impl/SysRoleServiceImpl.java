package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.domain.permission.SysRole;
import com.xmmufan.permission.repository.SysRoleRepository;
import com.xmmufan.permission.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/15
 * <p>
 * 用户权限接口详细类
 * </p>
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleRepository sysRoleRepository;

    @Autowired
    public SysRoleServiceImpl(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }

    @Override
    public SysRole findByRoleName(String roleName) {
        List<SysRole> roles = sysRoleRepository.findByRole(roleName);
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public List<SysRole> findAllRoles() {
        return sysRoleRepository.findAll();
    }

    @Override
    public SysRole saveOrUpdateRole(SysRole role) {
        return sysRoleRepository.saveAndFlush(role);
    }
}
