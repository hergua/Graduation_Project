package com.xmmufan.permission.service;


import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserInfo;

public interface UserInfoService {

    void saveOrUpdate(UserInfo userInfo);

    UserInfo queryByUser(User user);

    UserInfo queryById(String id);

}
