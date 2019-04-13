package cn.hergua.servicemodule.controller.api;

import cn.hergua.servicemodule.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/4/8
 * <p>
 *
 * </p>
 */

@RefreshScope
@RestController
@RequestMapping("/like")
@Slf4j
public class LikeController {

    @Autowired
    LikeService service;



}
