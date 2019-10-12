package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserAccount;
import com.xmmufan.permission.domain.rbac.UserInfo;
import com.xmmufan.permission.repository.UserAccountRepository;
import com.xmmufan.permission.repository.UserRepository;
import com.xmmufan.permission.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 *     用户服务详细接口类
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserAccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, UserAccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        throw new RuntimeException("this method shouldn't been call !");
    }

    @Override
    public User queryById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> queryAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User queryByAccount(UserAccount account) {
        return userRepository.queryByAccount_Id(account.getId());
    }

    @Override
    public User login(UserAccount account) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account.getUsername(), account.getPassword());
        subject.login(token);
        return userRepository.queryByAccount_Username(account.getUsername());
    }
}
