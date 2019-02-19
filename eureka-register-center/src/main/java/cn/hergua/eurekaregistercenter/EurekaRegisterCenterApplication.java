package cn.hergua.eurekaregistercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Hergua
 * @date 2018年12月22日
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaRegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegisterCenterApplication.class, args);
    }
}
