package com.xmmufan.permission.service.impl;

import com.google.protobuf.ServiceException;
import com.xmmufan.permission.domain.rbac.Permission;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserGroup;
import com.xmmufan.permission.repository.UserGroupRepository;
import com.xmmufan.permission.repository.UserRepository;
import com.xmmufan.permission.service.UserGroupService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 黄源钦
 * @Description
 * @Date 16:37
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository groupRepository;

    @Override
    public void createUserGroup(UserGroup group) {

    }

    @Override
    public void updateUserGroup(UserGroup group) {

    }

    @Override
    public void disableUserGroup(String groupId) {

    }

    @Override
    public void enableUserGroup(String groupId) {

    }

    @Override
    public void grantPermission(String groupId, List<Permission> grants) {

    }

    @Override
    public void revokePermission(String groupId, List<Permission> revokes) {

    }

    @Override
    public void addUserToGroup(String groupId, String userId) {
        groupRepository.findById(groupId).ifPresent(group ->
                userRepository.findById(userId).ifPresent(user -> {
            List<User> groupUser = CollectionUtils.isEmpty(group.getUsers()) ? new ArrayList<>() : group.getUsers();
            groupUser.add(user);
            group.setUsers(groupUser);
            groupRepository.saveAndFlush(group);
        }));

    }

    @Override
    public void removeUserFromGroup(String groupId, String userId) throws ServiceException {
        try{
            UserGroup userGroup = groupRepository.findById(groupId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
            List<User> groupUser = userGroup.getUsers();
            groupUser.remove(user);
            userGroup.setUsers(groupUser);
            groupRepository.saveAndFlush(userGroup);
        }catch (NullPointerException e){
            e.printStackTrace();
            throw new ServiceException("没有找到相关用户组&用户");
        }
    }
}
