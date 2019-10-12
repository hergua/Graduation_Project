package com.xmmufan.permission.constant.http;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public final class ResponseModel implements Serializable {

    private Integer statusCode;

    private String errMessage;

    private Object data;

    public static ResponseModel success(Object data){
        return new ResponseModel()
                .setStatusCode(HttpStatus.SC_OK)
                .setErrMessage("operation successful")
                .setData(data);
    }

    public static ResponseModel exceptionResponse(Exception e){
        return new ResponseModel()
                .setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .setErrMessage(e.getMessage())
                .setData(e.getMessage());
    }


}
