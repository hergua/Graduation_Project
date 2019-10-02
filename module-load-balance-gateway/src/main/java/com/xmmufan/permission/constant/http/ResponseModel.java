package com.xmmufan.permission.constant.http;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/11/13
 * <p>
 * </p>
 */
@Data
public final class ResponseModel implements Serializable {

    private Integer statusCode = 200;

    private String message = "response success";

    private Object data;
}
