package cn.hergua.gitconfigsever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
/**
 * @author Hergua
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class GitConfigSeverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitConfigSeverApplication.class, args);
    }
}
