package com.xmmufan.permission.controller;

import com.xmmufan.permission.constant.exception.AccountException;
import com.xmmufan.permission.constant.http.HttpStatusCode;
import com.xmmufan.permission.constant.http.ResponseModel;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.HostUnauthorizedException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/17
 * <p>
 *     基础restful格式接口的异常处理类
 *     shiro拦截没有权限的操作，会直接抛出异常，方法无返回
 *     增加异常捕获处理类，捕获对应的异常，进行统一的异常处理，返回状态等信息
 * </p>
 */
@RestController
public class BaseRestController {

    /**
     * @Description: shiro抛出未授权或授权异常时，返回未授权状态码403
     * @Param: []
     * @return: com.xmmufan.project.constant.http.ResponseModel
     * @Author: Mr.Hergua
     * @Date: 2018/11/17
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class, HostUnauthorizedException.class
    , UnauthorizedException.class})
    protected ResponseModel authenticationException() {
        ResponseModel model = new ResponseModel();
        model.setStatusCode(HttpStatusCode.UNAUTHORIZED);
        model.setMessage("未登录不允许访问该地址");
        return model;

    }

    /** 
     * @Description: shiro抛出权限异常时，返回授权不足状态码403 
     * @Param: []
     * @return: com.xmmufan.project.constant.http.ResponseModel 
     * @Author: Mr.Hergua
     * @Date: 2018/11/17 
     */ 
    @ExceptionHandler({UnauthorizedException.class})
    protected ResponseModel permissionAuthenticationException(){
        ResponseModel model = new ResponseModel();
        model.setStatusCode(HttpStatusCode.FORBIDDEN);
        model.setMessage("用户权限不足，无法访问该地址");
        return model;
    }

    /** 
     * @Description: 捕获密码加密时的异常，返回异常信息
     * @Param: [] 
     * @return: com.xmmufan.project.constant.http.ResponseModel 
     * @Author: Mr.Hergua
     * @Date: 2018/11/17 
     */ 
    @ExceptionHandler({AccountException.class})
    protected ResponseModel accountException(){
        ResponseModel model = new ResponseModel();
        model.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
        model.setMessage("传入参数不符合条件");
        return model;
    }

}
