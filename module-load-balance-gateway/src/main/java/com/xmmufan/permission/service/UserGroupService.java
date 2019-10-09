package com.xmmufan.permission.service;

import com.xmmufan.permission.domain.rbac.Permission;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserGroup;

import java.util.List;

/**
 * @author 黄源钦
 * @Description
 * @Date 16:13
 */
public interface UserGroupService {

    void createUserGroup(UserGroup group);

    void updateUserGroup(UserGroup group);

    void disableUserGroup(String id);

    void enableUserGroup(String id);

    void grantPermission(String id, List<Permission> grants);

    void revokePermission(String id, List<Permission> revokes);

    void addUser(String id ,User targetUser);

    void removeUser(String id, User targetUser);


}
