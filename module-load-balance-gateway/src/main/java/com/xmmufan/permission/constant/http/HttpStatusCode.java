package com.xmmufan.permission.constant.http;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 *     常见HTTP状态码封装
 * </p>
 */
public final class HttpStatusCode {

    /*
    * 所有方法执行成功，返回结果值有效时使用该状态
    * */
    public final static Integer OK = 200;

    /*
    * 方法执行成功，无需任何返回时，使用该状态
    * */
    public final static Integer NO_CONTENT = 204;

    /*
    * 资源已经永久发布到新的域名时，使用该状态
    * */
    public final static Integer MOVED_PERMANENTLY = 301;

    /*
    * 资源临时发布到新的域名时，使用该状态
    * */
    public final static Integer FOUND = 302;

    /*
    * 发送的请求参数不满足条件时，使用该状态
    * 例如：缺少用户名、密码等相关信息
    * */
    public final static Integer NOT_MODIFFED = 304;

    /*
    * 报文语法错误或者参数错误，使用该状态
    * */
    public final static Integer BAD_REQUEST = 400;

    /*
    * 授权失败时使用此状态码
    * */
    public final static Integer UNAUTHORIZED = 401;

    /*
    * 当用户的权限不足，拒绝访问，返回该状态
    * */
    public final static Integer FORBIDDEN = 403;

    /*
    * 发生未知错误时，返回该状态
    * */
    public final static Integer INTERNAL_SERVER_ERROR = 500;

    /*
    * 服务器超负载或者停机，返回该状态
    * */
    public final static Integer SERVICE_UNAVAILABLE = 503;

}
