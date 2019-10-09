package com.xmmufan.permission.domain.rbac;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author 黄源钦
 * @Description
 * @Date 11:03
 */
@Entity
@Data
@Builder
@AllArgsConstructor
public class UserGroup {

    @Id
    @GeneratedValue
    private String id;

    private String userGroupName;

    @Column(nullable = false, columnDefinition = "tinyint default true")
    private boolean enable;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userGroup_permission_mapper")
    private List<Permission> basicPermissions;

    @OneToMany
    private List<User> users;

    public UserGroup() {
    }
}
