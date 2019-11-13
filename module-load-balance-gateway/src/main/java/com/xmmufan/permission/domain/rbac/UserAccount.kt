package com.xmmufan.permission.domain.rbac

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable

import javax.persistence.*
import javax.rmi.CORBA.Stub
import javax.validation.constraints.NotEmpty

/**
 * @author 黄源钦
 * @Description
 * @Date 11:08
 */
@Entity
data class UserAccount(

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(length = 64, nullable = false)
    var id: String? = null,

    @field:NotEmpty
    var username: String,

    @field:NotEmpty
    var password: String

): Serializable {

    var salt: String? = null

    @Column(columnDefinition = "int default 8")
    var encryptedTimes: Int = 8

    @Column(columnDefinition = "tinyint default true")
    var isEnable: Boolean = true

    @Column(columnDefinition = "enum('MD5') default 'MD5'")
    var encryptedAlgorithm: String = "MD5"

    constructor():this(null,"","")
}
