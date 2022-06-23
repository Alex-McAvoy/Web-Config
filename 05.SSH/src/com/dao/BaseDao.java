package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/28 2:51
 * @Version: 1.0
 **/
public class BaseDao {
    private SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
