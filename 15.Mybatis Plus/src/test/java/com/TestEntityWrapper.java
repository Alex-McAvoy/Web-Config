package com;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bean.Employee;
import com.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description: 条件构造器 EntityWrapper 测试
 * @Author: Alex McAvoy
 * @Date: 2022/10/25 17:01
 * @Version: 1.0
 **/
public class TestEntityWrapper {

    private ApplicationContext iocContext = new
            ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper =
            iocContext.getBean("employeeMapper", EmployeeMapper.class);

    /**
     * @Description: 条件构造器，查询测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:04
     **/
    @Test
    public void testEntityWrapperSelect() {
        //分页查询 tbl_employee 表中，年龄在 18~50 间，性别为男，姓名为Tom的所有用户
        List<Employee> employees1 = employeeMapper.selectPage(
                new Page<Employee>(1, 2),
                new EntityWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom")
        );
        System.out.println("result1:" + employees1);

        //查询 tbl_employee 表中， 性别为女且名字中带有"老师"，或者邮箱中带有"a"
        List<Employee> employees2 = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .like("last_name", "老师")
                        //.or()    // SQL: (gender = ? AND last_name LIKE ? OR email LIKE ?)
                        .orNew()   // SQL: (gender = ? AND last_name LIKE ?) OR (email LIKE ?)
                        .like("email", "a")
        );
        System.out.println("result2:" + employees2);


        //查询性别为女的, 根据age进行排序(asc/desc), 简单分页
        List<Employee> employees3 = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .orderBy("age")
                        //.orderDesc(Arrays.asList(new String [] {"age"}))
                        .last("desc limit 1,3")
        );
        System.out.println("result3:" + employees3);
    }

    /**
     * @Description: 条件构造器，修改测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:11
     **/
    @Test
    public void testEntityWrapperUpdate() {
        Employee employee = new Employee();
        employee.setLastName("zxc");
        employee.setEmail("zxc@sina.com");
        employee.setGender(0);

        employeeMapper.update(employee,
                new EntityWrapper<Employee>()
                        .eq("last_name", "Tom")
                        .eq("age", 44)
        );
    }

    /**
     * @Description: 条件构造器，删除测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:12
     **/
    @Test
    public void testEntityWrapperDelete() {
        employeeMapper.delete(
                new EntityWrapper<Employee>()
                        .eq("last_name", "Tom")
                        .eq("age", 22)
        );
    }

    /**
     * @Description: 条件构造器，使用 Condition 查询
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/25 17:14
     **/
    @Test
    public void testConditionSelect() {
        //分页查询 tbl_employee 表中，年龄在 18~50 间，性别为男，姓名为Tom的所有用户
        List<Employee> employees = employeeMapper.selectPage(
                new Page<Employee>(1, 2),
                Condition.create()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom")
        );
        System.out.println("result:" + employees);
    }
}
