package com.xmmufan.permission.configuration

import com.xmmufan.permission.constant.http.ResponseModel
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author 黄源钦
 * @Description
 * @Date 11:24
 */
@ControllerAdvice
class ExceptionResponseHandler {

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun exceptionResponse(e: Exception): ResponseModel {
        return ResponseModel.exceptionResponse(e)
    }

}
