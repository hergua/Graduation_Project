package com.xmmufan.permission.domain.rbac

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable

import javax.persistence.*
import java.time.LocalDate

/**
 * @author 黄源钦
 * @Description
 * @Date 14:29
 */
@Entity
data class UserInfo (

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(length = 64, nullable = false)
    var id: String? = null,

    var nickName: String? = null,

    var gender: String? = null,

    var sign: String? = null,

    var birthday: LocalDate? = null,

    var email: String? = null,

    var location: String? = null,

    var headPicture: String? = null
): Serializable{
    companion object{
        fun empty() : UserInfo{
            return UserInfo()
        }
    }
}
