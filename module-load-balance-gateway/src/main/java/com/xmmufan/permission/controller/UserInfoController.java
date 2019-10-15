package com.xmmufan.permission.controller;

import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserInfo;
import com.xmmufan.permission.service.UserInfoService;
import com.xmmufan.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(path = "/userInfo")
@Slf4j
public class UserInfoController {

    private final UserInfoService userInfoService;

    private final UserService userService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService, UserService userService) {
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    @PostMapping("/saveOrUpdateUserInfo")
    public void saveUserInfo(UserInfo userInfo, String userId) throws InvocationTargetException, IllegalAccessException {
        User user = userService.queryById(userId);
        BeanUtils.copyProperties(user.getInfo(), userInfo);
        userService.updateUser(user);
    }

    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo(String infoId){
        return userInfoService.queryById(infoId);
    }


}
