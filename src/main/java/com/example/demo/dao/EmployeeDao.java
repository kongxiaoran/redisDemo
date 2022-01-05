package com.example.demo.dao;


import com.example.demo.bean.Employee;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;


/**
 * @Author: kxr
 * @Date: 2021/3/3
 * @Description
 */
@EnableCaching
public interface EmployeeDao {

    public long insert(Employee entity);

    public long update(Employee entity);

    public Employee getById(Integer id);

    public void delete(Integer id);

    public Employee getByLastName(String name);
}
