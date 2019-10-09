package com.xmmufan.permission.service.impl;

import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserInfo;
import com.xmmufan.permission.repository.UserInfoRepository;
import com.xmmufan.permission.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository infoRepository;

    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        infoRepository.saveAndFlush(userInfo);
    }

    @Override
    public UserInfo queryByUser(User user) {
        return infoRepository.queryByUser_Id(user.getId());
    }

    @Override
    public UserInfo queryById(String id) {
        return infoRepository.findById(id).orElse(null);
    }
}
