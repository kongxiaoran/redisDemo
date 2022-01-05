package com.example.demo.dao;


import com.example.demo.bean.Department;

/**
 * @Author: kxr
 * @Date: 2021/3/3
 * @Description
 */
public interface DepartmentDao {

    public long insert(Department entity);

    public long update(Department entity);

    public Department getById(Integer id);

    public void delete(Integer id);
}
