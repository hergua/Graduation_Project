package com.xmmufan.permission.domain.vo;

import com.xmmufan.permission.domain.rbac.Permission;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserGroup;
import com.xmmufan.permission.domain.rbac.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 黄源钦
 * @Description
 * @Date 16:23
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestVo {

    @NotNull
    private User user;

    @NotNull
    private UserGroup userGroup;

    private UserInfo userInfo;

    private List<Permission> customizePermission;

}
