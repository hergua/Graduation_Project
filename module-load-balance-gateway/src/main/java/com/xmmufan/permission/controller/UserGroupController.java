package com.xmmufan.permission.controller;

import com.xmmufan.permission.domain.rbac.UserGroup;
import com.xmmufan.permission.domain.vo.UserRequestVo;
import com.xmmufan.permission.service.UserGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.WebServiceException;

/**
 * @author 黄源钦
 * @Description
 * @Date 15:56
 */
@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @PostMapping("/saveGroup")
    public void saveGroup(@RequestBody @Valid UserGroup group){
        if (StringUtils.isBlank(group.getId())) {
            userGroupService.createUserGroup(group);
        } else {
            userGroupService.updateUserGroup(group);
        }
    }

    @PostMapping("/addUserToGroup")
    public void addUser(@RequestBody @Valid UserRequestVo vo){
        if (StringUtils.isAnyBlank(vo.getUser().getId(), vo.getUserGroup().getId())){
            throw new WebServiceException("参数缺失");
        }else {
            userGroupService.addUserToGroup(vo.getUserGroup().getId(), vo.getUserGroup().getId());
        }
    }

}
