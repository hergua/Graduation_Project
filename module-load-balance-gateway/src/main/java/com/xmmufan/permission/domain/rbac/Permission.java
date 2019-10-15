package com.xmmufan.permission.domain.rbac;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
public class Permission implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(length = 64, nullable = false)
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @Column(columnDefinition = "tinyint default true")
    private boolean enable = true;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_resource_mapper")
    private List<PermissionResource> resources;

}
