package com.xmmufan.permission.service;

import com.xmmufan.permission.constant.exception.AccountException;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserAccount;

/**
 * @author 黄源钦
 * @Description 用户账号接口
 * @Date 15:43
 */
public interface UserAccountService {

    void updatePassword(String id, String password) throws AccountException;

    void lockAccount(String id) throws AccountException;

    void unlockAccount(String id) throws AccountException;

    UserAccount findByUsername(String username);

}
