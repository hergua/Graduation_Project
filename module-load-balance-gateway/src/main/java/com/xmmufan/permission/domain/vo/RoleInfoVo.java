package com.xmmufan.permission.domain.vo;

import com.xmmufan.permission.domain.permission.SysRole;

import java.util.Objects;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 *     角色视图类，用于展示
 * </p>
 */
public class RoleInfoVo {

    private Integer id;
    private String role;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    RoleInfoVo(SysRole sysRole) {
        this.id = sysRole.getId();
        this.role = sysRole.getRole();
        this.description = sysRole.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleInfoVo that = (RoleInfoVo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(role, that.role) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, description);
    }
}
