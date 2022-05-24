package com.example.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private JedisPool jedisPool;

    private Jedis jedis;

    @BeforeEach
    public void initJedisConnect(){
        try {
            this.jedis = jedisPool.getResource();
        }catch (Exception e){
            System.out.println("jedis 获取连接 失败");
        }
    }

    @AfterEach
    public void destoryJedisConnect(){
        if(jedis != null){
            jedis.close();
        }
    }


    @Test
    public void testJedis() {
        System.out.println("dd");
        System.out.println(jedisPool);
        // 在连接池中得到Jedis连接
        Jedis jedis=jedisPool.getResource();
        jedis.set("haha","你好");
        jedis.set("name","wangpengcheng");
        //关闭当前连接
        jedis.close();

    }

    @Test
    void testSet(){
        // 插入数据，方法名称就是 redis 名称，非常简单
        String result = jedis.set("name","张三");
        System.out.println(result);
        // 获取数据
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testHash(){
        // 存入 hash 数据
        jedis.hset("user_1","name","Jack");
        jedis.hset("user_1","age","21");
        //获取
        Map<String, String> map = jedis.hgetAll("user_1");
        System.out.println(map);
    }



}
