package com.xmmufan.permission.service;


import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserAccount;
import com.xmmufan.permission.domain.rbac.UserInfo;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 * </p>
 */
public interface UserService {

    User saveUser(User user);

    User updateUser(User user);

    User queryById(String id);

    List<User> queryAllUser();

    User queryByAccount(UserAccount account);

    User login(UserAccount account);

}
