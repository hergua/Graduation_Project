package com.xmmufan.permission.domain.rbac

import lombok.Data
import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull
import java.io.Serializable

/**
 * @author 黄源钦
 * @Description
 * @Date 11:25
 */

@Entity
data class PermissionResource (

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(length = 64, nullable = false)
    var id: String? = null,

    @Column(columnDefinition = "enum('menu','button','link')")
    var type: String? = null,

    @NotNull
    var url: String? = null,

    @NotNull
    var operation: String? = null

): Serializable
