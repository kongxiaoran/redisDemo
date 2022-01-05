package com.example.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author kxr
 * @date 2022/1/3 7:57 PM
 * @description
 * 自定义key的生成策略
 */
@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
       return new KeyGenerator() {
           @Override
           public Object generate(Object target, Method method, Object... params) {
               return method.getName()+"["+ Arrays.asList(params).toString()+"]";
           }
       };
    }
}
