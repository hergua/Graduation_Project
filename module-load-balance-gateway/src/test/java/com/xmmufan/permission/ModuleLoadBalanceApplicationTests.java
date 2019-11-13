package com.xmmufan.permission;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ModuleLoadBalanceApplicationTests {

    private Jedis jedis;

    @Before
    public void setJedis(){
        jedis = new Jedis("139.199.64.246",6379);
        jedis.auth("a920553245");
        log.info("connect success");
    }

    @Test
    public void contextLoads() {
        jedis.set("name","hergua first operation");
        log.info(jedis.get("name"));
    }

}
