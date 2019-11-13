package com.xmmufan.permission.controller

import com.google.protobuf.ServiceException
import com.xmmufan.permission.aspect.EncryptedPasswordAndSalt
import com.xmmufan.permission.constant.exception.AccountException
import com.xmmufan.permission.domain.rbac.UserAccount
import com.xmmufan.permission.domain.rbac.UserInfo
import com.xmmufan.permission.domain.vo.UserInfoVo
import com.xmmufan.permission.service.UserAccountService
import com.xmmufan.permission.service.UserInfoService
import com.xmmufan.permission.service.UserService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 *
 *
 * 用户数据接口
 * 返回RESTFul格式数据
 *
 */

@RestController
@RequestMapping(path = ["/user"])
open class UserController
@Autowired constructor( private val userService: UserService,
                        private val accountService: UserAccountService,
                        private val infoService: UserInfoService) {
    
    /**
     * @Description: 该方法用于鉴权用户登录
     * @Param: [UserAccount] 传入的用户名密码
     * @return: ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @PostMapping(value = ["/subLogin"])
    open fun subLogin(@RequestBody account: UserAccount): UserInfoVo {
        val user = userService.login(account)
        return UserInfoVo(user)
    }


    /**
     * @Description: 用于保存用户账户信息
     * @Param: [UserAccount]
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @PostMapping(value = ["/addUser"])
    @EncryptedPasswordAndSalt
    @Throws(Exception::class)
    open fun addUser(@Valid @RequestBody account: UserAccount) {
        accountService.saveUserAccount(account)
    }


    /**
     * @Description: 获取所有用户信息
     * @Param: []
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @GetMapping(value = ["/queryAllUser"])
    open fun queryAllUser(): List<*> {
        return userService.queryAllUser()
    }

    /**
     * @Description: 用于判断用户是否存在
     * @Param: [username]
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/13
     */
    @GetMapping(value = ["/checkUsernameExist/{username}"])
    open fun checkUsernameExist(@PathVariable("username") @NotNull username: String): Boolean {
        return accountService.findByUsername(username) != null
    }

    @GetMapping(value = ["/disableUser/{userId}"])
    @Throws(AccountException::class, ServiceException::class)
    open fun disableUser(@PathVariable @NotNull userId: String) {
        userService.queryById(userId).account!!.id.let {
            if (it != null) {
                accountService.lockAccount(it)
            }
        }
    }

    @PostMapping(value = ["/info/update/{userId}"])
    open fun updateUserInfo(@RequestBody info: UserInfo, @PathVariable @NotNull userId: String) {
//        assert(StringUtils.isNotBlank(info.id)) { "ID参数缺失 " }
        infoService.saveOrUpdate(info, userId)
    }


}
