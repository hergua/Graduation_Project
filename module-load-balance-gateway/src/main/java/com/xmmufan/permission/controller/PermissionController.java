package com.xmmufan.permission.controller;

import com.xmmufan.permission.domain.rbac.Permission;
import com.xmmufan.permission.domain.rbac.PermissionResource;
import com.xmmufan.permission.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 黄源钦
 * @Description
 * @Date 15:01
 */
@RestController("/permission")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/queryAllPermission")
    public List<Permission> queryAllPermission(){
        return permissionService.queryAllPermission();
    }

    @GetMapping("/queryAllResource")
    public List<PermissionResource> queryAllResource(){
        return permissionService.queryAllResource();
    }

    @PostMapping("/addPermission")
    public void createPermission(@RequestBody @Valid Permission permission){
        permissionService.createPermission(permission);
    }

    @PostMapping("/deletePermission")
    public void deletePermission(String permissionId){
        permissionService.deletePermission(permissionId);
    }

    @PostMapping("/modifyPermission")
    public void modifyPermission(@RequestBody @Valid Permission permission){
        permissionService.modifyPermission(permission);
    }



}
