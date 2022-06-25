package com.mapper.test;

import com.mapper.beans.Employee;
import com.mapper.services.EmployeeService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/6/24 20:15
 * @Version: 1.0
 **/
public class EmployeeMapperTest {
    private ApplicationContext iocContainer = new ClassPathXmlApplicationContext("spring-context.xml");

    private EmployeeService employeeService = iocContainer.getBean(EmployeeService.class);

    /**
     * @Description: 测试通用 Mapper 的 selectOne 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/24 23:01
     **/
    @Test
    public void testSelectOne() {
        Employee employeeQueryCondition = new Employee(null, "bob", 5560.11, null);
        Employee employeeQueryResult = employeeService.getOne(employeeQueryCondition);
        System.out.println(employeeQueryResult);
    }

    /**
     * @Description: 测试通用 Mapper 的 selectAll 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/24 23:01
     **/
    @Test
    public void testSelectAll() {
        List<Employee> employees = employeeService.getAll();
        System.out.println(employees);
    }

    /**
     * @Description: 测试通用 Mapper 的 selectByPrimaryKey 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:02
     **/
    @Test
    public void testSelectByPrimaryKey() {
        Integer id = 3;
        Employee employee = employeeService.getEmployeeById(id);
        System.out.println(employee);
    }

    /**
     * @Description: 测试通用 Mapper 的 existsWithPrimaryKey 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:07
     **/
    @Test
    public void testExistWithPrimaryKey() {
        Integer id = 3;
        boolean exists = employeeService.isExists(id);
        System.out.println(exists);
    }

    /**
     * @Description: 测试通用 Mapper 的 insert 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:11
     **/
    @Test
    public void testInsert() {
        Employee employee = new Employee(null, "aaa", 1200.00, 45);
        employeeService.saveEmployee(employee);

        //在实体类对应主键中，使用@GeneratedValue(strategy = GenerationType.IDENTITY)以获得回写主键
        Integer id = employee.getId();
        System.out.println(id);
    }

    /**
     * @Description: 测试通用 Mapper 的 insertSelective 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:15
     **/
    @Test
    public void testInsertSelective() {
        Employee employee = new Employee(null, "aaa", null, 45);
        employeeService.saveEmployeeSelective(employee);
    }

    /**
     * @Description: 测试通用 Mapper 的 updateByPrimaryKeySelective 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:17
     **/
    @Test
    public void testUpdateByPrimaryKeySelective() {
        Employee employee = new Employee(8, "bbb", 123.00, 50);
        employeeService.updateEmployeeSelective(employee);
    }

    /**
     * @Description: 测试通用 Mapper 的 delete 方法，当为null时，会删除所有数据，一般开发中不适用
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:20
     **/
    @Test
    public void testDelete() {
        Employee employee = null;
        employeeService.deleteEmployee(employee);
    }

    /**
     * @Description: 测试通用 Mapper 的 deleteByPrimaryKey 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:24
     **/
    @Test
    public void testDeleteByPrimaryKey() {
        Integer id = 8;
        employeeService.deleteEmployeeById(id);
    }

    /**
     * @Description: 测试通用 Mapper 的 QBC 查询的 selectByExample 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:33
     **/
    @Test
    public void testSelectByExample() {
        //目标：WHERE (salary >? AND age <?) OR (salary <? AND age >?)

        //创建 Example 对象
        Example example = new Example(Employee.class);

        //设置排序信息
        example.orderBy("salary").asc().orderBy("age").desc();
        //设置去重
        example.setDistinct(true);
        //设置select字段，即要查出的字段
        example.selectProperties("name", "salary");

        //通过 Example 对象创建 Criteria 对象
        Criteria criteria1 = example.createCriteria();
        Criteria criteria2 = example.createCriteria();

        //在两个 Criteria 对象中分别设置查询条件，property 为实体类的属性名，value 为实体类的属性值
        criteria1.andGreaterThan("salary", 3000)
                .andLessThan("age", 25);
        criteria2.andLessThan("salary", 5000)
                .andGreaterThan("age", 30);

        //使用关键字组装 Criteria 对象
        example.or(criteria2);

        //执行查询
        List<Employee> employees = employeeService.getEmployeeListByExample(example);
        for (Employee employee : employees)
            System.out.println(employee);
    }

    /**
     * @Description: 测试通用 Mapper 的分页的 selectByRowBounds 方法
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/25 2:37
     **/
    @Test
    public void testSelectByRowBounds() {
        int pageNo = 3;
        int pageSize = 5;
        int index = (pageNo - 1) * pageSize;

        RowBounds rowBounds = new RowBounds(index, pageSize);
        Employee employee = new Employee();

        List<Employee> employees = employeeService.getEmployeeListByRowBounds(employee, rowBounds);
        for (Employee emp : employees)
            System.out.println(emp);
    }
}
