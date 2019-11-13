package com.xmmufan.permission.domain.rbac

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * @author 黄源钦
 * @Description
 * @Date 11:03
 */
@Entity
data class UserGroup(

        @Id
        @GeneratedValue(generator = "idGenerator")
        @GenericGenerator(name = "idGenerator", strategy = "uuid")
        @Column(length = 64, nullable = false)
        var id: String? = null,

        @NotNull
        @Column(unique = true)
        var userGroupName: String? = null,

        @NotNull
        var description: String? = null,

        @ManyToMany
        @JoinTable(name = "userGroup_permission_mapper")
        var basicPermissions: MutableList<Permission>? = null,

        @OneToMany
        @JsonIgnore
        var users: MutableList<User>? = null

) : Serializable
