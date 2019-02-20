package com.xmmufan.permission.service;

import com.xmmufan.permission.domain.permission.User;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 * </p>
 */
public interface UserService {

    User findByUsername(String username);

    User saveUser(User user);

    User updateUser(User user);

    User findById(Long id);

}
