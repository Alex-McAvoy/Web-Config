package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/2/28 2:51
 * @version 1.0
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
