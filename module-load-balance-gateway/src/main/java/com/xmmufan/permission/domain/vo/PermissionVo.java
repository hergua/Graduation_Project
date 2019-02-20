package com.xmmufan.permission.domain.vo;


import com.xmmufan.permission.domain.permission.SysPermission;

import java.util.Objects;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 *     权限视图类，去掉敏感信息字段，用于展示
 * </p>
 */
public class PermissionVo {

    private Integer id;

    private String name;

    private String permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public PermissionVo() {
    }

    PermissionVo(SysPermission sysPermission) {
        this.id = sysPermission.getId();
        this.name = sysPermission.getName();
        this.permission = sysPermission.getPermission();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PermissionVo that = (PermissionVo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, permission);
    }
}
