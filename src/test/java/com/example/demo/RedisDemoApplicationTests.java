package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private JedisPool jedisPool;


    @Test
    public void testJedis() {
        System.out.println("dd");
        System.out.println(jedisPool);
//        在连接池中得到Jedis连接
        Jedis jedis=jedisPool.getResource();
        jedis.set("haha","你好");
        jedis.set("name","wangpengcheng");
        //关闭当前连接
        jedis.close();

    }

}
