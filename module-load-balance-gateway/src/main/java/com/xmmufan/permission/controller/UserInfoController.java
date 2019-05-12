package com.xmmufan.permission.controller;


import com.xmmufan.permission.algorithm.SnowFlake;
import com.xmmufan.permission.constant.http.HttpStatusCode;
import com.xmmufan.permission.constant.http.ResponseModel;
import com.xmmufan.permission.domain.UserInfo;
import com.xmmufan.permission.domain.permission.User;
import com.xmmufan.permission.service.UserInfoService;
import com.xmmufan.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseModel saveUserInfo(UserInfo userInfo, Long userId) {
        ResponseModel model = new ResponseModel();
        try {
            User user = userService.findById(userId);
            userInfo.setUser(user);
            if (userInfo.getId() == null || userInfo.getId() == 0) {
                userInfo.setId(new SnowFlake().nextId());
                userInfoService.save(userInfo);
            } else if (userInfoService.queryById(userInfo.getId()) != null){
                userInfoService.update(userInfo);
            }else {
                throw new RuntimeException("unknown user status ! call administrator to resolve");
            }
            log.info("personal info save success   userId : " + userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.setMessage(e.getMessage());
            model.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
        }
        return model;
    }

    @GetMapping("/getUserInfo")
    public ResponseModel getUserInfo(Long userId){
        ResponseModel model = new ResponseModel();
        try {
            User user = userService.findById(userId);
            UserInfo userInfo = userInfoService.queryByUser(user);
            model.setData(userInfo);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            model.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            model.setMessage(e.getMessage());
        }
        return model;
    }


}
