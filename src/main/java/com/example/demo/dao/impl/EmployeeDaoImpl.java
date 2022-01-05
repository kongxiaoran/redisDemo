package com.example.demo.dao.impl;

import com.example.demo.bean.Employee;
import com.example.demo.dao.EmployeeDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

@EnableCaching
@Repository(value = "employeeDao")
public class EmployeeDaoImpl extends SqlSessionDaoSupport implements EmployeeDao {

    protected SqlSessionFactory sqlSessionFactory;

    public EmployeeDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public SqlSession getSqlSession() {
        return super.getSqlSession();
    }

    @Override
    public long insert(Employee entity) {
        return this.getSqlSession().insert("com.example.demo.dao.impl.EmployeeDaoImpl.insert",entity);
    }

    @Override
    public long update(Employee entity) {
        return this.getSqlSession().update("com.example.demo.dao.impl.EmployeeDaoImpl.update",entity);
    }

    @Override
    public Employee getById(Integer id) {
        return this.getSqlSession().selectOne("com.example.demo.dao.impl.EmployeeDaoImpl.getById",id);
    }

    @Override
    public void delete(Integer id) {
        this.getSqlSession().delete("com.example.demo.dao.impl.EmployeeDaoImpl.delete",id);
    }


    @Override
    public Employee getByLastName(String lastName) {
        return this.getSqlSession().selectOne("com.example.demo.dao.impl.EmployeeDaoImpl.getByLastName",lastName);
    }
}
