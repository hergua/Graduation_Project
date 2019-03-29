package cn.hergua.servicemodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * @author Hergua
 */
@SpringCloudApplication
public class ServiceModuleVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceModuleVideoApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("1024MB");
        factory.setMaxRequestSize("102400MB");
        return factory.createMultipartConfig();
    }
}
