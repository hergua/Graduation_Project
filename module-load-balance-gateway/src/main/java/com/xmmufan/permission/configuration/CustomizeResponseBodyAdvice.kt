package com.xmmufan.permission.configuration

import com.xmmufan.permission.constant.http.ResponseModel
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

import java.io.File

/**
 * @author 黄源钦
 * @Description
 * @Date 14:18
 */
@ControllerAdvice
class CustomizeResponseBodyAdvice : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.genericParameterType.typeName != ResponseModel::class.java.name
    }

    override fun beforeBodyWrite(body: Any?, returnType: MethodParameter, selectedContentType: MediaType, selectedConverterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): Any? {
        return if (body is File) { body } else { ResponseModel.success(body) }
    }

}
