package cn.hergua.servicemodule.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/24
 * <p>
 *
 * </p>
 */
@Component
@PropertySource("classpath:/qiniu.properties")
public class QINIUProperties {

    @Value("${accesskey}")
    public String ACCESS_KEY;

    @Value("${secretkey}")
    public String SECRET_KEY;

    @Value("${bucket}")
    public String BUCK_NAME;

    @Value("${cdns}")
    public String IMAGE_DOMAIN;

}
