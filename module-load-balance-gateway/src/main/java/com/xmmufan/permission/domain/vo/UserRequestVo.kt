package com.xmmufan.permission.domain.vo

import com.xmmufan.permission.domain.rbac.Permission
import com.xmmufan.permission.domain.rbac.User
import com.xmmufan.permission.domain.rbac.UserGroup
import com.xmmufan.permission.domain.rbac.UserInfo
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

import javax.validation.constraints.NotNull

/**
 * @author 黄源钦
 * @Description
 * @Date 16:23
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class UserRequestVo {

    @NotNull
    var user: User? = null
        set(user) {
            field = this.user
        }

    @NotNull
    var userGroup: UserGroup? = null
        set(userGroup) {
            field = this.userGroup
        }

    var userInfo: UserInfo? = null
        set(userInfo) {
            field = this.userInfo
        }

    var customizePermission: List<Permission>? = null
        set(customizePermission) {
            field = this.customizePermission
        }

}
