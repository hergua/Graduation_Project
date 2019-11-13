package com.xmmufan.permission.controller

import com.xmmufan.permission.domain.rbac.UserInfo
import com.xmmufan.permission.service.UserInfoService
import com.xmmufan.permission.service.UserService
import lombok.extern.slf4j.Slf4j
import org.apache.commons.beanutils.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.lang.reflect.InvocationTargetException

@RestController
@RequestMapping(path = ["/userInfo"])
open class UserInfoController @Autowired
constructor(private val userInfoService: UserInfoService, private val userService: UserService) {

    @PostMapping("/saveOrUpdateUserInfo")
    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    fun saveUserInfo(userInfo: UserInfo, userId: String) {
        val user = userService.queryById(userId)
        BeanUtils.copyProperties(user.info, userInfo)
        userService.updateUser(user)
    }

    @GetMapping("/getUserInfo")
    fun getUserInfo(infoId: String): UserInfo? {
        return userInfoService.queryById(infoId)
    }


}
