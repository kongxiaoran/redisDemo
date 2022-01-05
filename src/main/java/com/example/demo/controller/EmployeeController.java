package com.example.demo.controller;

import com.example.demo.bean.Employee;
import com.example.demo.dao.EmployeeDao;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author kxr
 * @date 2022/1/2 12:11 PM
 * @description
 */

/**
 *  1.开启基于注解的缓存
 *  2.标注缓存注解 @Cacheable @CacheEvict @CachePut
 *  3.Caching和CacheConfig注解的使用
 *  4.默认使用的缓存配置类是 SimpleCacheConfiguration
 *      默认使用的是ConcurrentMapCacheManager == ConcurrentMapCache：将数据保存在 ConcurrentMap<Object, Object>
 *      实际在开发中，使用缓存中间件：redis、memcache、ehcache；
 *  5.整合redis来作为缓存
 */
@RestController
@EnableCaching
@CrossOrigin
@ResponseBody
@RequestMapping(value = "/emp")
@CacheConfig(cacheNames = "emp")        //抽取该类所有缓存的公共配置 [cacheName都为emp]
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

//    @Cacheable(cacheNames = "emp",key = "#root.methodName+'['+#id+']'")  //将方法的运行结果进行缓存，以后相同的参数，直接从缓存中获取，不用调用方法；
//    @Cacheable(value = {"emp"},keyGenerator = "myKeyGenerator",          //使用自己的key生成器
//            condition = "#id>1 and #root.methodName eq 'getEmployee'",  //满足condition条件时，进行缓存
//            unless = "#id==0")      //当满足unless的条件时，不进行缓存
    @Cacheable(value = "emp",key = "#id")
    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable("id")Integer id){
        System.out.println("查询----------------");
        Employee byId = employeeDao.getById(id);
        return byId;
    }
    /**
     * @CachePut:即调用方法，又更新缓存数据；
     * 修改数据库的某个数据，同时更新缓存；
     */
//    @CachePut(value = "emp",key = "#result.id")
//    在@Cacheable后，不能使用result.* 。前者在获取result就需要创建cache的key，而CachePut中cache的key可以在方法运行后设置。
    @CachePut(value = "emp",key = "#employee.id")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Employee updateEmp(@RequestBody Employee employee){
        System.out.println("更新---------------");
        employeeDao.update(employee);
        return employee;
    }

    /**
     * @CacheEvict：缓存清除
     * 如果不写key，也默认使用的是方法的参数
     * allEntries = true 时，会删除emp中所有的key-value；
     * beforeInvoction = false，缓存的清除是否在方法之前执行；
     *  默认代表是在方法执行之后清除缓存，如果在方法执行过程中出现错误，
     *  则不能清除错误。
     *
     */
    @CacheEvict(value = "emp",key = "#id")
    @GetMapping(value = "/delete/{id}")
    public void  deleteEmp(@PathVariable("id") Integer id){
        System.out.println("清除--------------");
//        employeeDao.delete(id);
    }


    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                        @CachePut(value = "emp",key = "#result.email")
            })
    @GetMapping(value = "/getByLastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        Employee byLastName = employeeDao.getByLastName(lastName);
        return byLastName;
    }


}
