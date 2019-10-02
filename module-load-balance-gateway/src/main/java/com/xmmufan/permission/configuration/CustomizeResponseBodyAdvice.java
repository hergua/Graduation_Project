package com.xmmufan.permission.configuration;

import com.xmmufan.permission.constant.http.HttpStatusCode;
import com.xmmufan.permission.constant.http.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.File;

/**
 * @author 黄源钦
 * @Description
 * @Date 14:18
 */
@ControllerAdvice
@Slf4j
public class CustomizeResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        log.debug("MyResponseBodyAdvice==>supports:" + converterType);
        log.debug("MyResponseBodyAdvice==>supports:" + returnType.getClass());
        log.debug("MyResponseBodyAdvice==>supports:"
                + MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType));
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof File) {
            return body;
        } else {
            log.debug("MyResponseBodyAdvice==>beforeBodyWrite:" + returnType + "," + body);
            ResponseModel result = new ResponseModel();
            result.setStatusCode(HttpStatus.OK.value());
            result.setData(body);
            return body;
        }
    }
}
