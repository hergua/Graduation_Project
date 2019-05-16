package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.domain.permission.User;
import com.xmmufan.permission.repository.UserRepository;
import com.xmmufan.permission.service.UserService;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 *     用户服务详细接口类
 * </p>
 */
@Service
@Transactional(rollbackFor = UnauthorizedException.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {

        User cacheUser = findById(user.getId());
        cacheUser.setSalt(user.getSalt());
        cacheUser.setPassword(user.getPassword());
        return userRepository.saveAndFlush(cacheUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> queryAllUser() {
        return userRepository.findAll();
    }
}
