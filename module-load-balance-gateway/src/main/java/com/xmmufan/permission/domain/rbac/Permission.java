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
public class Permission {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private String description;

    private boolean enable;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_resource_mapper")
    private List<PermissionResource> resources;

}
