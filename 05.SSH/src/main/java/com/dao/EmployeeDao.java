package com.dao;

import com.entities.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Employee 的 DAO
 * @author Alex McAvoy
 * @date 2022/2/28 0:12
 * @version 1.0
 **/
public class EmployeeDao extends BaseDao {
    //显示信息
    public List<Employee> getAll() {
        String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department";
        return getSession().createQuery(hql).list();
    }

    //删除
    public void delete(Integer id) {
        String hql = "DELETE FROM Employee e WHERE e.id = ?";
        getSession().createQuery(hql).setInteger(0, id).executeUpdate();
    }

    //员工录入
    public void saveOrUpdate(Employee employee) {
        getSession().saveOrUpdate(employee);
    }

    //录入界面用户名验证ajax
    public Employee getEmployeeByName(String name) {
        String hql = "FROM Employee e WHERE e.Name = ?";
        Query query = getSession().createQuery(hql).setString(0, name);
        List<Employee> employees = query.list();
        if (employees.size() != 0) {
            Employee employee = (Employee) query.list().get(0);
            return employee;
        } else {
            return null;
        }
    }

    //编辑界面回显
    public Employee get(Integer id) {
        return (Employee) getSession().get(Employee.class,id);
    }
}
