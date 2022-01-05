package com.example.demo;

import com.example.demo.bean.Department;
import com.example.demo.bean.Employee;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Demo1ApplicationTests {

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;        //操作字符串

    @Autowired
    RedisTemplate redisTemplate;            //key-value【对象】

    @Autowired
    RedisTemplate<Object,Employee> employeeRedisTemplate;

    /**
     * Redis常见的五大数据类型
     * String、list、Set、Hash、ZSet
     * stringRedisTemplate.opsForValue()  操作字符串
     * stringRedisTemplate.opsForList()   操作列表
     * 等等
     *
     * 原理：
     *  1.引入redis的starter，容器中保存的是RedisCacheManager；
     *  2.RedisCacheManager帮我们创建RedisCache来作为缓存组件；RedisCache通过操作redis缓存数据
     *  3.默认保存数据k-v都是object，需要利用序列化保存，如果才能保存为json格式呢
     *      1).默认创建的RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object,Object>
     *      2).RedisTemplate<Object,Object> 是默认使用jdk的序列化机制
     *  4.
     * ---------------------------------
     */
    @Test
    public void testString(){
//        stringRedisTemplate.opsForValue().append("springboot","hello world");   //想redis中保存数据
//        String springboot = stringRedisTemplate.opsForValue().get("springboot");
//        System.out.println(springboot);
        stringRedisTemplate.opsForList().leftPush("myList","A");
        stringRedisTemplate.opsForList().leftPush("myList","B");
    }

    @Test
    public void testSet(){

        Employee byId = employeeDao.getById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("emp-01",byId);

        employeeRedisTemplate.opsForValue().set("emp-01",byId);
        //1.将数据以json的方式保存，将自己的对象转化为json
        //redisTemplate有默认的序列化规则，需要改变默认的序列化规则。
        employeeRedisTemplate.opsForValue().set("emp-01",byId);

    }

    @Test
    void contextLoads() {
        Department byId = departmentDao.getById(1);

        System.out.println(byId.toString());
    }

}
