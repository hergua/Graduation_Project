package cn.hergua.servicemodule.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hergua
 * @version: 1.0     2018/10/21
 * <p>
 * </p>
 */

@RefreshScope
@RestController
public class DemoController {

    @Value("${spring.http.encoding.charset}")
    String data;

    @GetMapping("index")
    public String index(){
        return data;
    }


}
