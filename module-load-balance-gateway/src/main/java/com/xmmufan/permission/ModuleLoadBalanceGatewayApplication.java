package com.xmmufan.permission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * @author Hergua
 */
@SpringCloudApplication
@EnableZuulProxy
public class ModuleLoadBalanceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleLoadBalanceGatewayApplication.class, args);
    }

}
