package com.xmmufan.permission.domain.rbac

import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import javax.validation.constraints.NotNull
import java.io.Serializable

/**
 * @author 黄源钦
 * @Description
 * @Date 11:03
 */
@Entity
data class Permission (
        @Id
        @GeneratedValue(generator = "idGenerator")
        @GenericGenerator(name = "idGenerator", strategy = "uuid")
        @Column(length = 64, nullable = false)
        var id: String? = null,

        @NotNull
        var name: String? = null,

        @NotNull
        var description: String? = null,

        @OneToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "permission_resource_mapper")
        var resources: List<PermissionResource>? = null
): Serializable
