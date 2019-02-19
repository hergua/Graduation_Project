package cn.hergua.moduleloadbalance;

import cn.hergua.moduleloadbalance.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author Hergua
 */
@SpringCloudApplication
@EnableZuulProxy
@RefreshScope
public class ModuleLoadBalanceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleLoadBalanceGatewayApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
