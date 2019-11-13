package com.xmmufan.permission.constant.http

import org.apache.http.HttpStatus

import java.io.Serializable

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 *
 */
class ResponseModel(val statusCode: Int) : Serializable {

    var errMessage: String? = null

    var data: Any? = null

    companion object {

        fun success(data: Any?): ResponseModel {
            return ResponseModel(HttpStatus.SC_OK).apply {
                this.data = data
            }
        }

        fun exceptionResponse(e: Exception): ResponseModel {
            return ResponseModel(HttpStatus.SC_INTERNAL_SERVER_ERROR).apply {
                this.data = e.message
                this.data = e.message
            }
        }
    }


}
