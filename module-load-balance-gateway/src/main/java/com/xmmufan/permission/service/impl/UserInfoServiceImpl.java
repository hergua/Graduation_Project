package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.domain.UserInfo;
import com.xmmufan.permission.domain.permission.User;
import com.xmmufan.permission.repository.UserInfoRepository;
import com.xmmufan.permission.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public void save(UserInfo userInfo) {
        repository.save(userInfo);
    }

    @Override
    public void update(UserInfo userInfo) {
        repository.saveAndFlush(userInfo);
    }

    @Override
    public UserInfo queryByUser(User user) {
        return repository.queryByUser(user);
    }

    @Override
    public UserInfo queryById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
