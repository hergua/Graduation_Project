package com.xmmufan.permission.controller;

import com.xmmufan.permission.aspect.EncryptedPasswordAndSalt;
import com.xmmufan.permission.constant.http.HttpStatusCode;
import com.xmmufan.permission.constant.exception.AccountInfoMissingException;
import com.xmmufan.permission.constant.http.ResponseModel;
import com.xmmufan.permission.constant.permission.AccountState;
import com.xmmufan.permission.domain.rbac.UserAccount;
import com.xmmufan.permission.service.UserAccountService;
import com.xmmufan.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
public class UserController extends BaseRestController {

    private final UserService userService;

    private final UserAccountService accountService;

    @Autowired
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
    public ResponseModel subLogin(@RequestBody UserAccount account) {
        ResponseModel model = new ResponseModel();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(account.getUsername(), account.getPassword());
            subject.login(token);
//            model.setData(getUserInfoVo(account.getUsername()));
        } catch (LockedAccountException e) {
            model.setMessage("该账户已被锁定，无法进行登录");
        } catch (AuthenticationException e) {
            model.setStatusCode(HttpStatusCode.UNAUTHORIZED);
            model.setMessage("用户名或密码错误");
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            model.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            model.setMessage("服务器内部发生了错误");
        }
        return model;
    }

    /**
     * @Description: 通过用户名查找用户，获取用户信息
     * @Param: [username]
     * @return: com.xmmufan.project.domain.vo.UserInfoVo
     * @Author: Mr.Hergua
     * @Date: 2018/11/14
     */
//    private UserInfoVo getUserInfoVo(String username) throws UnknownAccountException {
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            throw new UnknownAccountException("there is no user info has been directed. ");
//        } else {
//            return new UserInfoVo(user);
//        }
//    }


    /**
     * @Description: 用于保存用户账户信息
     * @Param: [user]
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @PostMapping(value = "/addUser")
    @EncryptedPasswordAndSalt
    public ResponseModel addUser(@RequestBody UserAccount account) {
        ResponseModel model = new ResponseModel();
        try {
            if (StringUtils.isAnyBlank(account.getUsername(), account.getPassword())) {
                throw new AccountInfoMissingException("用户名或密码缺失");
            }
            if (accountService.existUsername(account.getUsername())) {
                log.warn(accountService.findByUsername(account.getUsername()).toString());
                throw new AccountException("用户名已存在");
            }
            accountService.saveUserAccount(account);
        } catch (AccountException e) {
            model.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            model.setMessage("用户名已存在");
        } catch (Exception e) {
            model.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            model.setMessage("服务器内部出错");
        }
        return model;
    }



    /**
     * @Description: 获取所有用户信息
     * @Param: []
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @GetMapping(value = "/queryAllUser")
    public ResponseModel queryAllUser() {
        ResponseModel model = new ResponseModel();
        try {
            model.setData(userService.queryAllUser());
        } catch (Exception e) {
            model.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            model.setMessage("服务器内部出错");
        }
        return model;
    }

//    /**
//     * @Description: 用于判断用户是否存在
//     * @Param: [username]
//     * @return: com.xmmufan.project.constant.http.ResponseModel
//     * @Author: Mr.Hergua
//     * @Date: 2018/11/13
//     */
//    @GetMapping(value = "/checkUsernameExist")
//    public ResponseModel checkUsernameExist(String username) {
//        ResponseModel model = new ResponseModel();
//        try {
//            if (userService.findByUsername(username) != null) {
//                throw new AccountException("用户名已存在");
//            }
//            model.setMessage("该用户名可以使用");
//        } catch (AccountException e) {
//            model.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
//            model.setMessage(e.getMessage());
//        }
//        return model;
//    }


}
