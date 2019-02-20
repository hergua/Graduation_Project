package com.xmmufan.permission.domain.vo;

import com.xmmufan.permission.domain.permission.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 *     用户信息试图类
 * </p>
 */
public class UserInfoVo {

    private Long id;

    private String username;

    private String name;

    private byte state;

    private Set<RoleInfoVo> roleSet;

    private Set<PermissionVo> permissionSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Set<RoleInfoVo> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<RoleInfoVo> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<PermissionVo> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<PermissionVo> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public UserInfoVo() {
    }

    public UserInfoVo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.state = user.getState();
        Set<RoleInfoVo> roleInfoVos = new HashSet<>();
        Set<PermissionVo> permissionVos = new HashSet<>();
        user.getRoleList().forEach(sysRole -> {
            roleInfoVos.add(new RoleInfoVo(sysRole));
            sysRole.getPermissions().forEach(sysPermission -> permissionVos.add(new PermissionVo(sysPermission)));
        });
        this.setPermissionSet(permissionVos);
        this.setRoleSet(roleInfoVos);
    }
}
