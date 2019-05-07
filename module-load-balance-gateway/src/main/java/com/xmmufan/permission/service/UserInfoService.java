package com.xmmufan.permission.service;

import com.xmmufan.permission.domain.UserInfo;
import com.xmmufan.permission.domain.permission.User;

public interface UserInfoService {

    void save(UserInfo userInfo);

    void update(UserInfo userInfo);

    UserInfo queryByUser(User user);

    UserInfo queryById(Long id);

}
