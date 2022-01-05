package com.example.demo.dao.impl;

import com.example.demo.bean.Department;
import com.example.demo.dao.DepartmentDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "departmentDao")
public class DepartmentDaoImpl extends SqlSessionDaoSupport implements DepartmentDao {

    @Autowired
    protected SqlSessionFactory sqlSessionFactory;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public SqlSession getSqlSession() {
        return super.getSqlSession();
    }

    @Override
    public long insert(Department entity) {
        return this.getSqlSession().insert("com.example.demo.dao.impl.DepartmentDaoImpl.insert",entity);
    }

    @Override
    public long update(Department entity) {
        return this.getSqlSession().update("com.example.demo.dao.impl.DepartmentDaoImpl.update",entity);
    }

    @Override
    public Department getById(Integer id) {
        return this.getSqlSession().selectOne("com.example.demo.dao.impl.DepartmentDaoImpl.getById",id);
    }

    @Override
    public void delete(Integer id) {
        this.getSqlSession().delete("com.example.demo.dao.impl.DepartmentDaoImpl.delete",id);
    }
}
