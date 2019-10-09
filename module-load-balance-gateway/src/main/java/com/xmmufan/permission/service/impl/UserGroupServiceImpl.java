package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.domain.rbac.Permission;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserGroup;
import com.xmmufan.permission.service.UserGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 黄源钦
 * @Description
 * @Date 16:37
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Override
    public void createUserGroup(UserGroup group) {

    }

    @Override
    public void updateUserGroup(UserGroup group) {

    }

    @Override
    public void disableUserGroup(String id) {

    }

    @Override
    public void enableUserGroup(String id) {

    }

    @Override
    public void grantPermission(String id, List<Permission> grants) {

    }

    @Override
    public void revokePermission(String id, List<Permission> revokes) {

    }

    @Override
    public void addUser(String id, User targetUser) {

    }

    @Override
    public void removeUser(String id, User targetUser) {

    }
}
