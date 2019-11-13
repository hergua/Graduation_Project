package com.xmmufan.permission.domain.rbac

import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import java.io.Serializable

/**
 * @author 黄源钦
 * @Description
 * @Date 11:03
 */
@Entity
data class User(
        @Id
        @GeneratedValue(generator = "idGenerator")
        @GenericGenerator(name = "idGenerator", strategy = "uuid")
        @Column(length = 64, nullable = false)
        var id: String? = null,

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_group_id")
        var userGroup: UserGroup? = null,

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "info_id")
        var info: UserInfo? = null,

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "account_id")
        var account: UserAccount? = null,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_permission_mapper", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "permission_id")])
        var customizePermission: List<Permission>? = null
) : Serializable
