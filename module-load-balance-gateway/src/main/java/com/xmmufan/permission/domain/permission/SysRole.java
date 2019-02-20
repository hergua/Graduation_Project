package com.xmmufan.permission.domain.permission;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/2
 * <p>
 * </p>
 */

@Entity
public class SysRole implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String role;
    private String description;
    private Boolean available = Boolean.FALSE;


    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;

    @ManyToMany
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name=
            "user_id")})
    private List<User> users;


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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", permissions=" + permissions +
                '}';
    }
}
