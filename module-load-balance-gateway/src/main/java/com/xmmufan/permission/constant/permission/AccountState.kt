package com.xmmufan.permission.constant.permission

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0
 * @Date 2018/11/13
 *
 *
 * 常见的用户账号状态码封装
 *
 */
object AccountState {

    /*
    * 用户锁定的状态
    **/
    val LOCKED: Byte = 2

    /*
    * 用户未激活的状态
    * */
    val NOT_CERTIFIED: Byte = 0

    /*
    * 正常状态
    * */
    val ACTIVE: Byte = 1

}
