package com.xmmufan.permission.service;

import com.google.protobuf.ServiceException;
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

    void disableUserGroup(String groupId);

    void enableUserGroup(String groupId);

    void grantPermission(String groupId, List<Permission> grants);

    void revokePermission(String groupId, List<Permission> revokes);

    void addUserToGroup(String groupId ,String userId);

    void removeUserFromGroup(String groupId, String userId) throws ServiceException;


}
