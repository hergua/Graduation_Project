package com.xmmufan.permission.domain.vo;

import com.xmmufan.permission.domain.rbac.Permission;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserGroup;
import com.xmmufan.permission.domain.rbac.UserInfo;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * @author 黄源钦
 * @Description
 * @Date 13:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class UserInfoVo {

    private UserGroup userGroup;

    private UserInfo userInfo;

    private Map<String,String> permissions;

    public UserInfoVo(User user){
       this.setUserInfo(user.getInfo())
               .setUserGroup(user.getUserGroup())
               .setPermissions(computePermissionStrMap(user));
    }

    /**
     * @author huangyq
     * @Description 把用户下的特别权限，以及用户所属的用户组的权限进行收集
     * @Date 2019/10/11 15:48
     * @param user 用户
     * @return 权限描述map
     */
    private Map<String,String> computePermissionStrMap(User user){
        Optional<List<Permission>> customizePermissionList = Optional.ofNullable(user.getCustomizePermission());
        Optional<UserGroup>  userGroup =  Optional.ofNullable(user.getUserGroup());

        Map<String,String> permissionMap = new HashMap<>(50);
        customizePermissionList.ifPresent(permissionList ->
                permissionList.forEach(permission ->
                        permission.getResources().forEach(resource ->
                                permissionMap.put(resource.getUrl(), resource.getOperation()))));

        userGroup.flatMap(group ->
                Optional.ofNullable(group.getBasicPermissions()))
                .ifPresent(permissionList ->
                        permissionList.forEach(permission ->
                                permission.getResources().forEach(resource ->
                                        permissionMap.put(resource.getUrl(), resource.getOperation()))));

        return permissionMap;
    }

}
