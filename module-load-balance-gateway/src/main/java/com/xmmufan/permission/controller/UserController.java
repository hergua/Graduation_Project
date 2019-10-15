package com.xmmufan.permission.controller;

import com.xmmufan.permission.aspect.EncryptedPasswordAndSalt;
import com.xmmufan.permission.domain.rbac.User;
import com.xmmufan.permission.domain.rbac.UserAccount;
import com.xmmufan.permission.domain.vo.UserInfoVo;
import com.xmmufan.permission.service.UserAccountService;
import com.xmmufan.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 * 用户数据接口
 * 返回RESTFul格式数据
 * </p>
 */

@RestController
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserAccountService accountService;

    public UserController(UserService userService, UserAccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    /**
     * @Description: 该方法用于鉴权用户登录
     * @Param: [user] 传入的用户名密码
     * @return: ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @PostMapping(value = "/subLogin")
    public UserInfoVo subLogin(@Valid UserAccount account) {
        User user = userService.login(account);
        return new UserInfoVo(user);
    }



    /**
     * @Description: 用于保存用户账户信息
     * @Param: [user]
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @PostMapping(value = "/addUser")
    @EncryptedPasswordAndSalt
    public void addUser(@Valid UserAccount account) throws Exception {
        accountService.saveUserAccount(account);
    }


    /**
     * @Description: 获取所有用户信息
     * @Param: []
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @GetMapping(value = "/queryAllUser")
    public List queryAllUser() {
        return userService.queryAllUser();
    }

    /**
     * @Description: 用于判断用户是否存在
     * @Param: [username]
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @GetMapping(value = "/checkUsernameExist")
    public boolean checkUsernameExist(String username) {
        if (accountService.findByUsername(username) != null) {
            throw new AccountException("用户名已存在");
        }
        return true;
    }


}
